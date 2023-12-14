package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.userInterface.DeleteUserUseCase;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Override
    @Transactional
    public void DeleteUser(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);

        // Remove all followers of the user
        followRepository.deleteByFollowee(user.orElse(null));

        // Remove all following relationships of the user
        followRepository.deleteByFollower(user.orElse(null));

        // Delete user's posts
        postRepository.deleteByUser(user.orElse(null));

        // Delete the user
        userRepository.deleteById(userId);
    }
}
