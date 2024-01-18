package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.dtos.userDtos.*;
import com.fontys.s3.grooveshare.business.userInterface.GetUserUseCase;
import com.fontys.s3.grooveshare.business.userInterface.GetUsersUseCase;
import com.fontys.s3.grooveshare.business.userInterface.LogInUserUseCase;
import com.fontys.s3.grooveshare.business.userInterface.UpdateUserUseCase;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;


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
        // Arrange
        int page = 0;
        int size = 10;

        GetAllUsersRequest expectedRequest = GetAllUsersRequest.builder()
                .page(page)
                .size(size)
                .build();

        GetAllUsersResponse expectedResponse = new GetAllUsersResponse();

        when(getUsersUseCase.getUsers(expectedRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GetAllUsersResponse> responseEntity = userController.getAllUsers(page, size);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

//    @Test
//    @WithMockUser(username = "testuser", roles = "USER")
//    void testUpdateUser() {
//        long userId = 1L;
//        UpdateUserRequest updateRequest = new UpdateUserRequest(/* provide necessary data for the request */);
//
//        ResponseEntity<Void> responseEntity = userController.updateUser(userId, updateRequest);
//
//        assertEquals(204, responseEntity.getStatusCodeValue()); // 204 for no content
//
//        verify(updateUserUseCase, times(1)).updateUser(updateRequest);
//    }

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