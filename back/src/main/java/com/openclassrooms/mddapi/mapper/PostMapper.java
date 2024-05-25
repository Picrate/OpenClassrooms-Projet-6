package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimplePostDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface PostMapper {

    PostDto postToPostDto(Post post);
    Post simplePostDtoToPost(SimplePostDto simplePostDto);
    /*
    Posts List Mapper
     */
    List<PostDto> postsToPostDtos(List<Post> posts);
    /*
    Comments Mappers
    */
    List<CommentDto> commentsToCommentDtos(List<Comment> comments);
    CommentDto commentToCommentDto(Comment comment);
    /*
    Topic Mapper
     */
    TopicDto topicToTopicDto(Topic topic);
    Topic topicDtoToTopic(TopicDto topicDto);




}
