package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostResponse;
import com.fontys.s3.grooveshare.persistance.GenreRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreatePostUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private CreatePostUseCaseImpl createPostUseCase;

    @Test
    public void testCreatePost() {
        // Mock data
        CreatePostRequest request = new CreatePostRequest();
        request.setUserId(1L);
        request.setContent("Test post content");
        request.setGenreId(1L);
        request.setTrackId("testTrackId");

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

        // Mock repository behaviors
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(genreRepository.findById(1L)).thenReturn(Optional.of(genreEntity));
        Mockito.when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);

        // Execute the use case
        CreatePostResponse response = createPostUseCase.createPost(request);

        // Verify the result
        assertEquals(1L, response.getPostId());
        assertEquals("Test post content", response.getContent());
        assertEquals("testTrackId", response.getTrackId());
    }

    @Test
    public void testCreatePostWithNonExistingUser() {
        // Mock data
        CreatePostRequest request = new CreatePostRequest();
        request.setUserId(2L); // User with ID 2 does not exist in the mock repository
        request.setContent("Test post content");

        // Mock repository behaviors
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Execute the use case and expect an exception
        assertThrows(IllegalArgumentException.class, () -> createPostUseCase.createPost(request));
    }
}