package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimplePostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    PostMapper postMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPostDtoById(String id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return postMapper.postToPostDto(post.get());
        } else {
            return null;
        }
    }

    public Post getPostById(String id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return post.get();
        }else {
            return null;
        }
    }

    public String addCommentToPost(SimpleUserDto user, PostCommentDto postCommentDto){
        String postId = postCommentDto.getPost_id();
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post postObj = post.get();
            Comment comment = new Comment(user, postCommentDto.getComment());
            postObj.addComment(comment);
            postRepository.save(postObj);
            return postObj.getId();
        }
        else return null;

    }

    public String createNewPost(SimplePostDto postDto, SimpleUserDto author) {
        Post newPost = postMapper.simplePostDtoToPost(postDto);
        newPost.setAuthor(author);
        this.postRepository.save(newPost);
        return newPost.getId();
    }

    // Return a list of post for a specific topic
    public List<PostDto> getAllPostsForTopic(String topic){
        return postMapper.postsToPostDtos(this.postRepository.findByTopicEqualsIgnoreCaseOrderByCreatedAtDesc(topic));
    }

    // Return a list of post for user subscribed topics
    public List<PostDto> getAllPostsForTopics(List<String> topics){
        Criteria orCriteria = new Criteria();
        List<Criteria> topicsCriterias = new ArrayList<>();
        for(String topic : topics){
            Criteria criteria = Criteria.where("topic").is(topic);
            topicsCriterias.add(criteria);
        }
        orCriteria.orOperator(topicsCriterias);
        Query query = new Query(orCriteria);
        return postMapper.postsToPostDtos(mongoTemplate.find(query, Post.class));
    }


}
