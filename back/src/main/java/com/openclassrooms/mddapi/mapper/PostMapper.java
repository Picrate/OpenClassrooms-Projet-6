package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimplePostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.Author;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    Author Mappers
     */
    SimpleUserDto authorToSimpleUserDto(Author author);
    Author simpleUserDtoToAuthor(SimpleUserDto simpleUserDto);

}
