package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

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

    public void addCommentToPost(SimpleUserDto user, PostCommentDto postCommentDto){
        String postId = postCommentDto.getPost_id();
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post postObj = post.get();
            Comment comment = new Comment(user, postCommentDto.getComment());
            postObj.addComment(comment);
            postRepository.save(postObj);
        }
        // TODO manage post does not exists exception
    }


}
