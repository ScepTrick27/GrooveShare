package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetUserPostCountResponse;
import com.fontys.s3.grooveshare.domain.UserPostCount;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserPostCountUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UserPostCountUseCaseImpl userPostCountUseCase;

    @Test
    public void testGetUserPostCount() {
        when(postRepository.getUserPostCounts()).thenReturn(Collections.singletonList(new UserPostCount()));

        GetUserPostCountResponse response = userPostCountUseCase.getUserPostCount();

        assertEquals(1, response.getUserPostCounts().size());
    }

}