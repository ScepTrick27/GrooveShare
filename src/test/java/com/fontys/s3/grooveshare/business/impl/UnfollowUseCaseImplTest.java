package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.UnfollowRequest;
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
class UnfollowUseCaseImplTest {
    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UnfollowUseCaseImpl unfollowUseCase;

    @Test
    public void testUnfollow() {
        Long followerId = 1L;
        Long followeeId = 2L;

        UserEntity followerEntity = new UserEntity();
        UserEntity followeeEntity = new UserEntity();

        FollowEntity followEntity = new FollowEntity();
        followEntity.setFollower(followerEntity);
        followEntity.setFollowee(followeeEntity);

        UnfollowRequest request = new UnfollowRequest(followerId, followeeId);

        when(userRepository.findById(followerId)).thenReturn(Optional.of(followerEntity));
        when(userRepository.findById(followeeId)).thenReturn(Optional.of(followeeEntity));
        when(followRepository.findByFollowerAndFollowee(followerEntity, followeeEntity)).thenReturn(Optional.of(followEntity));

        unfollowUseCase.unfollow(request);

        verify(followRepository, times(1)).delete(followEntity);
    }

    @Test
    public void testUnfollowWhenFollowRelationshipNotFound() {
        Long followerId = 1L;
        Long followeeId = 2L;

        UnfollowRequest request = new UnfollowRequest(followerId, followeeId);

        when(userRepository.findById(followerId)).thenReturn(Optional.of(new UserEntity()));
        when(userRepository.findById(followeeId)).thenReturn(Optional.of(new UserEntity()));
        when(followRepository.findByFollowerAndFollowee(any(), any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> unfollowUseCase.unfollow(request));
    }
}