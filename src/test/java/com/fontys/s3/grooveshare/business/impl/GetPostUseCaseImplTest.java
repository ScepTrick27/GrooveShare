package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPostUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostUseCaseImpl getPostUseCase;

    @Test
    void getPost_WithValidPostId_ShouldReturnPost() {
        UserEntity user1 = UserEntity.builder().userId(1L).username("user1").build();

        PostEntity expectedPost = PostEntity.builder().postId(1L).content("Post 1 content").user(user1).build();

        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.of(expectedPost));

        Optional<Post> result = getPostUseCase.getPost(postId);

        assertTrue(result.isPresent());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void getPost_WithInvalidPostId_ShouldReturnEmptyOptional() {
        Long postId = 2L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        Optional<Post> result = getPostUseCase.getPost(postId);

        assertTrue(result.isEmpty());
        verify(postRepository, times(1)).findById(postId);
    }
}