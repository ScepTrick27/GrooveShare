package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.LikeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class UnlikePostUseCaseImplTest {
    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UnlikePostUseCaseImpl unlikePostUseCase;

    @Test
    void unlikePost_Success() {
        // Arrange
        LikePostRequest request = new LikePostRequest(1L, 1L);
        LikeEntity mockLikeEntity = new LikeEntity();
        Mockito.when(likeRepository.findByUserUserIdAndPostPostId(1L, 1L))
                .thenReturn(Optional.of(mockLikeEntity));

        // Act
        LikePostResponse response = unlikePostUseCase.unlikePost(request);

        // Assert
        assertTrue(response.isSuccess());
        Mockito.verify(likeRepository, Mockito.times(1)).delete(mockLikeEntity);
    }

    @Test
    void unlikePost_NoLikeFound() {
        // Arrange
        LikePostRequest request = new LikePostRequest(1L, 1L);
        Mockito.when(likeRepository.findByUserUserIdAndPostPostId(1L, 1L))
                .thenReturn(Optional.empty());

        // Act
        LikePostResponse response = unlikePostUseCase.unlikePost(request);

        // Assert
        assertFalse(response.isSuccess());
        Mockito.verify(likeRepository, Mockito.never()).delete(any());
    }
}