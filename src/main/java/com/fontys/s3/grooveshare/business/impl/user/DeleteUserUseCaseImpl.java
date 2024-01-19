package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.business.user.DeleteUserUseCase;
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
    public void deleteUser(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            // Throw InvalidUserIdException if the user is not found
            throw new InvalidUserIdException("User with ID " + userId + " not found.");
        }

        // Remove all followers of the user
        followRepository.deleteByFollowee(user.get());

        // Remove all following relationships of the user
        followRepository.deleteByFollower(user.get());

        // Delete user's posts
        postRepository.deleteByUser(user.get());

        // Delete the user
        userRepository.deleteById(userId);
    }
    }
