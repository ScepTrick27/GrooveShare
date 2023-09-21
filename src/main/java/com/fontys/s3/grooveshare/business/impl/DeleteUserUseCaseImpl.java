package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.DeleteUserUseCase;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void DeleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
