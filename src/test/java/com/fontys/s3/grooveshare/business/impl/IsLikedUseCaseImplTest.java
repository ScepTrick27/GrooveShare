package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.IsLikedUseCase;
import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IsLikedUseCaseImplTest {
    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private IsLikedUseCaseImpl isLikedUseCase;

    @Test
    public void isLiked_ReturnsTrue_WhenUserLikedPost() {
        LikePostRequest request = new LikePostRequest(1L, 2L); // Replace with actual user and post IDs
        when(likeRepository.existsByUserUserIdAndPostPostId(request.getUserId(), request.getPostId())).thenReturn(true);

        IsLikedResponse response = isLikedUseCase.isLiked(request);

        assertTrue(response.isLiked());
    }

    @Test
    public void isLiked_ReturnsFalse_WhenUserDidNotLikePost() {
        LikePostRequest request = new LikePostRequest(1L, 2L); // Replace with actual user and post IDs
        when(likeRepository.existsByUserUserIdAndPostPostId(request.getUserId(), request.getPostId())).thenReturn(false);

        // Act
        IsLikedResponse response = isLikedUseCase.isLiked(request);

        // Assert
        assertFalse(response.isLiked());
    }
}