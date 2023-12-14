package com.fontys.s3.grooveshare.business.impl.postImpl;

import com.fontys.s3.grooveshare.business.dtos.postDtos.UpdatePostRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.business.impl.postImpl.UpdatePostUseCaseImpl;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePostUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UpdatePostUseCaseImpl updatePostUseCase;

    @Test
    void updatePost_ShouldUpdatePost() {
        PostEntity postEntity = PostEntity.builder()
                .postId(1L)
                .content("Post Content 1")
                .build();

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity));

        UpdatePostRequest updateRequest = UpdatePostRequest.builder()
                .postId(1L)
                .content("Updated Post Content")
                .build();

        updatePostUseCase.updatePost(updateRequest);

        verify(postRepository, times(1)).findById(1L);
        assertEquals("Updated Post Content", postEntity.getContent());
    }

    @Test
    void updatePost_NonexistentPost_ShouldThrowInvalidUserIdException() {
        UpdatePostRequest request = new UpdatePostRequest(1L, "Updated post content");

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InvalidUserIdException.class, () -> updatePostUseCase.updatePost(request));

        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, never()).save(any());
    }
}