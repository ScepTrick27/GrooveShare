package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.business.dtos.*;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:*", allowCredentials = "true")
public class PostController {
    private final PostRepository postRepository;
    private final CreatePostUseCase createPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final GetAllPostsUseCase getAllPostsUseCase;
    private final GetPostUseCase getPostUseCase;

    @PostMapping()
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request){
        CreatePostResponse response = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllPostsResponse> getAllPosts() {
        GetAllPostsRequest request = GetAllPostsRequest.builder().build();
        GetAllPostsResponse response = getAllPostsUseCase.getPosts(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId")Long postId){
        final Optional<Post> postOptional = getPostUseCase.getPost(postId);
        if (postOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(postOptional.get());
    }

    @PutMapping("{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") Long postId,
                                               @RequestBody @Valid UpdatePostRequest request){
        request.setPostId(postId);
        updatePostUseCase.updatePost(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        deletePostUseCase.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
