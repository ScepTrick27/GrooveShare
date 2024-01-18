package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.FindPostsByFollowersUseCase;
import com.fontys.s3.grooveshare.business.RecommendationByGenreUseCase;
import com.fontys.s3.grooveshare.business.dtos.*;
import com.fontys.s3.grooveshare.business.dtos.postDtos.*;
import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersResponse;
import com.fontys.s3.grooveshare.business.postInterface.*;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import jakarta.annotation.security.RolesAllowed;
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
    private final UserPostCountUseCase userPostCountUseCase;
    private final FindPostsByFollowersUseCase findPostsByFollowers;
    private final RecommendationByGenreUseCase recommendationByGenreUseCase;
    private final GetPostsByGenreIdUseCase getPostsByGenreIdUseCase;

    @PostMapping()
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request){
        CreatePostResponse response = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllPostsResponse> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetAllPostsRequest request = GetAllPostsRequest.builder()
                .page(page)
                .size(size)
                .build();
        GetAllPostsResponse response = getAllPostsUseCase.getPosts(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byGenre/{genreId}")
    public ResponseEntity<GetPostsByGenreIdResponse> getPostsByGenreId(@PathVariable("genreId")Long genreId) {
        GetPostsByGenreIdRequest request = GetPostsByGenreIdRequest.builder()
                .genreId(genreId)
                .build();
        GetPostsByGenreIdResponse response = getPostsByGenreIdUseCase.getPostsByGenreId(request);
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
@RolesAllowed({"ADMIN"})
    @GetMapping("stats")
    public ResponseEntity<GetUserPostCountResponse> getUserPostCounts() {
        GetUserPostCountResponse response = userPostCountUseCase.getUserPostCount();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findPostsByFollowers/{userId}")
    public ResponseEntity<FindPostsByFollowersResponse> findPostsByFollowers(@PathVariable Long userId) {
        FindPostsByFollowersResponse response = findPostsByFollowers.findPostsByLoggedInUserAndFollowers(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/recommended/{userId}")
    public ResponseEntity<RecommendationByGenreResponse> getRecommendedPosts(@PathVariable Long userId) {
        RecommendationByGenreResponse response = recommendationByGenreUseCase.getRecommendedPosts(userId);
        return ResponseEntity.ok(response);
    }
}
