package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.DeleteUserUseCase;
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

    @Override
    @Transactional
    public void DeleteUser(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
            postRepository.deleteByUser(user.orElse(null));
            this.userRepository.deleteById(userId);
    }
}
