package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.business.dtos.*;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private GetUserUseCase getUserUseCase;

    @Mock
    private GetUsersUseCase getUsersUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private LogInUserUseCase loginUserUseCase;


    @InjectMocks
    private UserController userController;

        @Test
        void testGetUser() {
            long userId = 1L;
            User user = User.builder()
                    .userId(userId)
                    .username("JohnDoe")
                    .password("password123")
                    .description("User description")
                    .userGender(UserGenderEntity.MALE)
                    .userRole(new UserRoleEntity())
                    .build();

            when(getUserUseCase.getUser(userId)).thenReturn(Optional.of(user));

            ResponseEntity<User> responseEntity = userController.getUser(userId);

            assertEquals(200, responseEntity.getStatusCodeValue());
            assertEquals(user, responseEntity.getBody());
        }

        @Test
        void testGetUserNotFound() {
            long userId = 1L;

            when(getUserUseCase.getUser(userId)).thenReturn(Optional.empty());

            ResponseEntity<User> responseEntity = userController.getUser(userId);

            assertEquals(404, responseEntity.getStatusCodeValue());
            assertNull(responseEntity.getBody());
        }

    @Test
    void testGetAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse expectedResponse = new GetAllUsersResponse(/* mock your response data here */);

        when(getUsersUseCase.getUsers(request)).thenReturn(expectedResponse);

        ResponseEntity<GetAllUsersResponse> responseEntity = userController.getAllUsers();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;
        UpdateUserRequest updateRequest = new UpdateUserRequest(/* provide necessary data for the request */);

        ResponseEntity<Void> responseEntity = userController.updateUser(userId, updateRequest);

        assertEquals(204, responseEntity.getStatusCodeValue()); // 204 for no content

        verify(updateUserUseCase, times(1)).updateUser(updateRequest);
    }

    @Test
    void testLogin() {
        LogInUserRequest loginRequest = new LogInUserRequest();

        LogInUserResponse expectedResponse = LogInUserResponse.builder().accessToken("dummyToken").build();

        when(loginUserUseCase.loginUser(loginRequest)).thenReturn(expectedResponse);

        ResponseEntity<LogInUserResponse> responseEntity = userController.login(loginRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    }