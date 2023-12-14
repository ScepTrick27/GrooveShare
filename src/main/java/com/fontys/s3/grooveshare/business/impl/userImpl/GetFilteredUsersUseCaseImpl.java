package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.userInterface.GetFilteredUsersUseCase;
import com.fontys.s3.grooveshare.business.dtos.userDtos.GetFilteredUsersResponse;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFilteredUsersUseCaseImpl implements GetFilteredUsersUseCase {
    public final UserRepository userRepository;


    @Override
    public GetFilteredUsersResponse filteredSearch(String username) {
        List<UserEntity> filteredUsers = userRepository.filteredSearch(username);
        final GetFilteredUsersResponse response = new GetFilteredUsersResponse();
              List<User> users = filteredUsers
                .stream()
               .map(UserConverter::convert)
                .toList();
        response.setUsers(users);
        return response;
    }

}
