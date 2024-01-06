package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.FollowUseCase;
import com.fontys.s3.grooveshare.business.dtos.FollowRequest;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.FollowEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowUseCaseImpl implements FollowUseCase {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    @Override
    public void follow(FollowRequest request) {
        UserEntity follower = userRepository.findById(request.getFollowerId())
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        UserEntity followee = userRepository.findById(request.getFolloweeId())
                .orElseThrow(() -> new RuntimeException("Followee not found"));

        FollowEntity follow1 = FollowEntity.builder()
                .follower(follower)
                .followee(followee)
                .build();

        followRepository.save(follow1);
    }
}
