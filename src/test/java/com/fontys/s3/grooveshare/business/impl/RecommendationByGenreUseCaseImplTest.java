package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.RecommendationByGenreResponse;
import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationByGenreUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private RecommendationByGenreUseCaseImpl recommendationByGenreUseCase;

    @Test
    void getRecommendedPosts_ReturnsCorrectResponse() {
        Long userId = 1L;

        List<PostEntity> likedPosts = Arrays.asList(
                createMockPostEntity(1L, "Rock"),
                createMockPostEntity(2L, "Pop"),
                createMockPostEntity(3L, "Rock"),
                createMockPostEntity(4L, "Jazz")
        );

        when(postRepository.findLikedPostsByUserId(userId)).thenReturn(likedPosts);
        when(postRepository.findAll()).thenReturn(Arrays.asList(
                createMockPostEntity(5L, "Rock"),
                createMockPostEntity(6L, "Pop"),
                createMockPostEntity(7L, "Hip Hop"),
                createMockPostEntity(8L, "Rock")
        ));

        RecommendationByGenreResponse response = recommendationByGenreUseCase.getRecommendedPosts(userId);

        assertEquals(4, response.getRecommendedPosts().size());
        assertEquals(5L, response.getRecommendedPosts().get(0).getPostId());
        assertEquals(6L, response.getRecommendedPosts().get(1).getPostId());
        assertEquals(7L, response.getRecommendedPosts().get(2).getPostId());

        verify(postRepository, times(1)).findLikedPostsByUserId(userId);
        verify(postRepository, times(1)).findAll();
        verify(postRepository, times(4)).countLikesForPost(anyLong());
    }

    private PostEntity createMockPostEntity(Long postId, String genreName) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);

        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(postId);
        postEntity.setGenre(genreEntity);
        return postEntity;
    }
}