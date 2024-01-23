package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.LikeEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikePostUseCaseImplTest {
    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private LikePostUseCaseImpl likePostUseCase;

    @Test
    void likePost_SuccessfullyLikedPost() {
        Long userId = 1L;
        Long postId = 2L;

        LikePostRequest request = new LikePostRequest(userId, postId);
        LikePostResponse expectedResponse = new LikePostResponse();
        expectedResponse.setSuccess(true);

        UserEntity user = new UserEntity();
        user.setUserId(userId);

        PostEntity post = new PostEntity();
        post.setPostId(postId);

        when(userRepository.getById(userId)).thenReturn(user);
        when(postRepository.getById(postId)).thenReturn(post);
        when(likeRepository.existsByUserUserIdAndPostPostId(userId, postId)).thenReturn(false);

        LikePostResponse response = likePostUseCase.likePost(request);

        assertTrue(response.isSuccess());
        verify(likeRepository, times(1)).save(any(LikeEntity.class));
    }

    @Test
    void likePost_UserAlreadyLikedPost() {
        Long userId = 1L;
        Long postId = 2L;

        LikePostRequest request = new LikePostRequest(userId, postId);
        LikePostResponse expectedResponse = new LikePostResponse();
        expectedResponse.setSuccess(false);  // User already liked the post

        when(likeRepository.existsByUserUserIdAndPostPostId(userId, postId)).thenReturn(true);

        LikePostResponse response = likePostUseCase.likePost(request);

        assertFalse(response.isSuccess());
        verify(userRepository, never()).getById(anyLong());
        verify(postRepository, never()).getById(anyLong());
        verify(likeRepository, never()).save(any(LikeEntity.class));
    }
}