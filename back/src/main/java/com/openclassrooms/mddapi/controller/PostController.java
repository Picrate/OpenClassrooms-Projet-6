package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.SimplePostDto;
import com.openclassrooms.mddapi.dto.SimpleUserDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4002", maxAge = 3600, allowCredentials="true")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> createPost(@RequestBody SimplePostDto postDto, UriComponentsBuilder ucb) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        SimpleUserDto author = this.userService.getSimpleUserDtoByEmailOrUsername(currentPrincipalName);
        String newPostId = this.postService.createNewPost(postDto, author);
        return ResponseEntity.ok(new MessageResponse(newPostId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        PostDto dto = postService.getPostDtoById(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping("/topic")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PostDto>> getPostsByTopic(@RequestParam String topic) {
        if(topic == null || topic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (this.postService.getAllPostsForTopic(topic) == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } else {
            return ResponseEntity.ok(this.postService.getAllPostsForTopic(topic));
        }
    }

    @GetMapping("/topics")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TopicDto>> getTopicsByTitle(@RequestParam String title) {
        if(title == null || title.isEmpty()) {
            List<TopicDto> topics = this.postService.getAllTopics();
            if(topics == null) {
                return ResponseEntity.internalServerError().build();
            } else {
                return ResponseEntity.ok(topics);
            }
        }
        List<TopicDto> topics = this.postService.getTopicByTitleContainingIgnoreCase(title);
        if(topics == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(topics);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
