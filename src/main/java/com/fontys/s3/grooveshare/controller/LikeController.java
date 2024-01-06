package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.IsLikedUseCase;
import com.fontys.s3.grooveshare.business.LikePostUseCase;
import com.fontys.s3.grooveshare.business.UnlikePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:*", allowCredentials = "true")
public class LikeController {
    private final LikePostUseCase likePostUseCase;
    private final UnlikePostUseCase unlikePostUseCase;
    private final IsLikedUseCase isLikedUseCase;

    @PostMapping("/like")
    public ResponseEntity<LikePostResponse> likePost(@RequestBody LikePostRequest request) {
        LikePostResponse response = likePostUseCase.likePost(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/unlike")
    public ResponseEntity<LikePostResponse> unlikePost(@RequestBody LikePostRequest request) {
        LikePostResponse response = unlikePostUseCase.unlikePost(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hasLiked")
    public ResponseEntity<IsLikedResponse> hasUserLikedPost(@RequestParam Long userId,
                                                            @RequestParam Long postId) {
        LikePostRequest likePostRequest = LikePostRequest.builder().userId(userId).postId(postId).build();
        IsLikedResponse response = isLikedUseCase.isLiked(likePostRequest);
        return ResponseEntity.ok(response);
    }

}
