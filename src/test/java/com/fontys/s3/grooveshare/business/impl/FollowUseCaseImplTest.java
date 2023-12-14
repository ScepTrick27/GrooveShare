package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.FollowRequest;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.FollowEntity;
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
class FollowUseCaseImplTest {
    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FollowUseCaseImpl followUseCase;

    @Test
    public void testFollow() {
        FollowRequest request = new FollowRequest(1L, 2L);

        UserEntity followerEntity = new UserEntity();
        UserEntity followeeEntity = new UserEntity();

        when(userRepository.findById(1L)).thenReturn(Optional.of(followerEntity));
        when(userRepository.findById(2L)).thenReturn(Optional.of(followeeEntity));

        followUseCase.follow(request);

        verify(followRepository, times(1)).save(any(FollowEntity.class));
    }
}