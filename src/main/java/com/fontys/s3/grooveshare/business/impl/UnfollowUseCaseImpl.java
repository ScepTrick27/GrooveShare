package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UnfollowUseCase;
import com.fontys.s3.grooveshare.business.dtos.UnfollowRequest;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.FollowEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnfollowUseCaseImpl implements UnfollowUseCase {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    @Override
    public void unfollow(UnfollowRequest request) {
        UserEntity follower = userRepository.findById(request.getFollowerId())
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        UserEntity followee = userRepository.findById(request.getFolloweeId())
                .orElseThrow(() -> new RuntimeException("Followee not found"));

        FollowEntity follow = followRepository.findByFollowerAndFollowee(follower, followee)
                .orElseThrow(() -> new RuntimeException("Follow relationship not found"));

        followRepository.delete(follow);
    }
}
