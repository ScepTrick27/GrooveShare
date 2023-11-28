package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.CreatePostResponse;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
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

    @InjectMocks
    private CreatePostUseCaseImpl createPostUseCase;

    @Test
    public void testCreatePost() {
        // Mock data
        CreatePostRequest request = new CreatePostRequest();
        request.setUserId(1L);
        request.setContent("Test post content");

        UserEntity userEntity = UserEntity.builder()
                .userId(1L)
                .username("testUser")
                .password("testPassword")
                .build();

        PostEntity postEntity = PostEntity.builder()
                .postId(1L)
                .content("Test post content")
                .user(userEntity)
                .build();

        // Mock repository behaviors
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);

        // Execute the use case
        CreatePostResponse response = createPostUseCase.createPost(request);

        // Verify the result
        assertEquals(1L, response.getPostId());
        assertEquals("Test post content", response.getContent());
    }

    @Test
    public void testCreatePostWithNonExistingUser() {
        // Mock data
        CreatePostRequest request = new CreatePostRequest();
        request.setUserId(2L); // User with ID 2 does not exist in the mock repository
        request.setContent("Test post content");

        // Mock repository behaviors
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Execute the use case
        CreatePostResponse response = createPostUseCase.createPost(request);

        // Verify the result
        assertNull(response.getPostId());
        assertNull(response.getContent());
    }
}