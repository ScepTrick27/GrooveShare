package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.GetUserUseCase;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId).map(UserConverter::convert);
    }
}
