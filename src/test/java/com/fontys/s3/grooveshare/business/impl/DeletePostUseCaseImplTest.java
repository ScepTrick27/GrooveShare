package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletePostUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private DeletePostUseCaseImpl deletePostUseCase;
    @Test
    void deletePost_Successful() {
        Long postId = 1L;

        deletePostUseCase.deletePost(postId);

        verify(postRepository).deleteById(postId);
    }

    @Test
    void deletePost_PostNotFound() {
        Long postId = 1L;

        doThrow(new EmptyResultDataAccessException(1)).when(postRepository).deleteById(postId);

        assertThrows(EmptyResultDataAccessException.class, () -> deletePostUseCase.deletePost(postId));

        verify(postRepository).deleteById(postId);
    }

    @Test
    void deletePost_NullPostId() {
        assertThrows(IllegalArgumentException.class, () -> deletePostUseCase.deletePost(null));

        verifyNoInteractions(postRepository);
    }

    @Test
    void deletePost_NegativePostId() {
        assertThrows(IllegalArgumentException.class, () -> deletePostUseCase.deletePost(-1L));
        verifyNoInteractions(postRepository);
    }

}