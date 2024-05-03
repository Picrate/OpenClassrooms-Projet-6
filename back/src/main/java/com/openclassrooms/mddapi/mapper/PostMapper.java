package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimplePostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface PostMapper {

    @Mapping(target = "created_at", source = "createdAt")
    public PostDto postToPostDto(Post post);

    @Mapping(target = "createdAt", source = "created_at")
    public Post postDtoToPost(PostDto postDto);

    public Post simplePostDtoToPost(SimplePostDto simplePostDto);

    SimpleUserDto userToSimpleUserDto(User user);

    /*
       Posts List Mapper
     */
    public List<PostDto> postsToPostDtos(List<Post> posts);
    /*
    Comments Mappers
     */

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

    @Mapping(target = "created_at", source = "createdAt")
    CommentDto commentToCommentDto(Comment comment);


}
