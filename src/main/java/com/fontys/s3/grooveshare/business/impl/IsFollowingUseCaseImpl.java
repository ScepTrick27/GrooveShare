package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.IsFollowingUseCase;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IsFollowingUseCaseImpl implements IsFollowingUseCase {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    @Override
    public boolean isFollowing(Long followerId, Long followeeId) {
        UserEntity follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        UserEntity followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new RuntimeException("Followee not found"));

        return followRepository.findByFollowerAndFollowee(follower, followee).isPresent();
    }
}
