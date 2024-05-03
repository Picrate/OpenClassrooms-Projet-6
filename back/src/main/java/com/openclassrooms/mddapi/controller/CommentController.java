package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.dto.PostCommentDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Slf4j
public class CommentController {

    private final PostService postService;
    private final UserService userService;

    public CommentController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createComment(@RequestBody PostCommentDto postComment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("Creating comment for " + currentPrincipalName);
        if (postComment == null || postComment.getPost_id() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid post comment"));
        }else {
            Post postToAddComment = postService.getPostById(postComment.getPost_id());
            if (postToAddComment == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Invalid post comment"));
            } else {
                SimpleUserDto commentUser = this.userService.getSimpleUserDtoByEmail(currentPrincipalName);
                postService.addCommentToPost(commentUser, postComment);
                return ResponseEntity.ok(new MessageResponse("Comment created"));
            }
        }
    }

}
