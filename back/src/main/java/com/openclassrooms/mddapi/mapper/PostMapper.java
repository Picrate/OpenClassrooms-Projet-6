package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "created_at", source = "createdAt")
    public PostDto postToPostDto(Post post);

    public Post postDtoToPost(PostDto postDto);

    SimpleUserDto userToSimpleUserDto(User user);

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

    CommentDto commentToCommentDto(Comment comment);

    CommentDto commentDtoToComment(CommentDto commentDto);
}
