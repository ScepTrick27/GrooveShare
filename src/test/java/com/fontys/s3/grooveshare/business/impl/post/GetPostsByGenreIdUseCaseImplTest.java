package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdResponse;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPostsByGenreIdUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostsByGenreIdUseCaseImpl getPostsByGenreIdUseCase;

    @Test
    void getPostsByGenreId_WithValidGenreId_ShouldReturnPosts() {
        // Arrange
        Long genreId = 1L;
        GetPostsByGenreIdRequest request = new GetPostsByGenreIdRequest(genreId);
        UserEntity userEntity = UserEntity.builder()
                .userId(1L)
                .username("testUser")
                .password("testPassword")
                .build();

        GenreEntity genreEntity = GenreEntity.builder()
                .id(1L)
                .genre("TestGenre")
                .build();

        PostEntity postEntity = PostEntity.builder()
                .postId(1L)
                .content("Test post content")
                .user(userEntity)
                .genre(genreEntity)
                .trackId("testTrackId")
                .build();
        List<PostEntity> postEntities = Arrays.asList(
                postEntity
        );
        when(postRepository.findByGenreId(genreId)).thenReturn(postEntities);
        when(postRepository.countLikesForPost(anyLong())).thenReturn(0L);

        // Act
        GetPostsByGenreIdResponse response = getPostsByGenreIdUseCase.getPostsByGenreId(request);

        // Assert
        assertFalse(response.getPosts().isEmpty());
        assertEquals(1, response.getPosts().size());
        assertEquals("Test post content", response.getPosts().get(0).getContent());
    }

    @Test
    void getPostsByGenreId_WithInvalidGenreId_ShouldReturnEmptyList() {
        // Arrange
        Long genreId = 1L;
        GetPostsByGenreIdRequest request = new GetPostsByGenreIdRequest(genreId);
        when(postRepository.findByGenreId(genreId)).thenReturn(Collections.emptyList());

        // Act
        GetPostsByGenreIdResponse response = getPostsByGenreIdUseCase.getPostsByGenreId(request);

        // Assert
        assertTrue(response.getPosts().isEmpty());
    }


}