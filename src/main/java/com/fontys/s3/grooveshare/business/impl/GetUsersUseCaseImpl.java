package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.DTOs.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.DTOs.GetAllUsersResponse;
import com.fontys.s3.grooveshare.business.GetUsersUseCase;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers(GetAllUsersRequest request) {
        List<UserEntity> results = userRepository.getAllUsers();

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> students = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(students);

        return response;
    }
}
