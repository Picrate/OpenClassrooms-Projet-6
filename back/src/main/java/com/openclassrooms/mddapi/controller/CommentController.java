package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.dto.PostCommentDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final String  INVALID_COMMENT = "Invalid post comment";

    private final PostService postService;
    private final UserService userService;

    public CommentController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createComment(@RequestBody PostCommentDto postComment, UriComponentsBuilder ucb) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (postComment == null || postComment.getPost_id() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse(INVALID_COMMENT);
        }else {
            Post postToAddComment = postService.getPostById(postComment.getPost_id());
            if (postToAddComment == null) {
                return ResponseEntity.badRequest().body(new MessageResponse(INVALID_COMMENT));
            } else {
                SimpleUserDto commentUser = this.userService.getSimpleUserDtoByEmail(currentPrincipalName);
                String postId = postService.addCommentToPost(commentUser, postComment);
                if (postId == null) {
                    return ResponseEntity.badRequest().body(new MessageResponse(INVALID_COMMENT));
                } else {
                    URI locationOfRelatedPost = ucb
                            .path("/api/posts/{id}")
                            .buildAndExpand(postId)
                            .toUri();
                    return ResponseEntity.created(locationOfRelatedPost).build();
                }
            }
        }
    }

}
