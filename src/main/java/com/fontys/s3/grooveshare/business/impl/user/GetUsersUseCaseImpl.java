package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.GetAllUsersResponse;
import com.fontys.s3.grooveshare.business.user.GetUsersUseCase;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers(GetAllUsersRequest request) {
        Page<UserEntity> results = userRepository.getAllUsers(
                PageRequest.of(request.getPage(), request.getSize())
        );

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> students = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(students);
        response.setTotalPages(results.getTotalPages());
        response.setTotalUsers(results.getTotalElements());

        return response;
    }
}
