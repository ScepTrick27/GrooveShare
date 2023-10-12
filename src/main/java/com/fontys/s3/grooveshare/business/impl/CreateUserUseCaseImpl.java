package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.CreateUserUseCase;
import com.fontys.s3.grooveshare.business.DTOs.CreateUserRequest;
import com.fontys.s3.grooveshare.business.DTOs.CreateUserResponse;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;


    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .description(request.getDescription())
                .userGender(request.getUserGender())
                .build();
        return userRepository.save(newUser);
    }
}
