package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void getUsersShouldReturnAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(UserEntity.builder().userId(1L).username("user1").build());
        userEntities.add(UserEntity.builder().userId(2L).username("user2").build());

        when(userRepository.getAllUsers()).thenReturn(userEntities);

        GetAllUsersRequest request = GetAllUsersRequest.builder().build();

        GetAllUsersResponse response = getUsersUseCase.getUsers(request);

        verify(userRepository, times(1)).getAllUsers();

        assertEquals(2, response.getUsers().size());
        assertEquals("user1", response.getUsers().get(0).getUsername());
        assertEquals("user2", response.getUsers().get(1).getUsername());
    }
}