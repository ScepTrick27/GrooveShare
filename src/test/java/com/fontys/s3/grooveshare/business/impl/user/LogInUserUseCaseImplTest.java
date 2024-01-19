package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserResponse;
import com.fontys.s3.grooveshare.business.exception.InvalidCredentialsException;
import com.fontys.s3.grooveshare.configuration.security.token.AccessTokenEncoder;
import com.fontys.s3.grooveshare.configuration.security.token.impl.AccessTokenImpl;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.RoleEnum;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogInUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LogInUserUseCaseImpl logInUserUseCase;

    @Test
    void loginUser_ValidCredentials_ReturnsAccessToken() {
        // Arrange
        LogInUserRequest request = new LogInUserRequest("username", "password");
        UserEntity userEntity = UserEntity.builder()                .username("test1")
                .password("username")
                .userGender(UserGenderEntity.MALE)
                .description("password")
                .userRole(UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build())
                .build();

        when(userRepository.getUserEntityByUsername(request.getUsername())).thenReturn(userEntity);
        when(passwordEncoder.matches(request.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessTokenImpl.class))).thenReturn("fakeAccessToken");

        // Act
        LogInUserResponse response = logInUserUseCase.loginUser(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getAccessToken());
        assertEquals("fakeAccessToken", response.getAccessToken());

        // Verify interactions
        verify(userRepository, times(1)).getUserEntityByUsername(request.getUsername());
        verify(passwordEncoder, times(1)).matches(request.getPassword(), userEntity.getPassword());
        verify(accessTokenEncoder, times(1)).encode(any(AccessTokenImpl.class));
    }

    @Test
    void loginUser_InvalidUsername_ThrowsInvalidCredentialsException() {
        // Arrange
        LogInUserRequest request = new LogInUserRequest("nonexistentUser", "password");

        when(userRepository.getUserEntityByUsername(request.getUsername())).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> logInUserUseCase.loginUser(request));

        // Verify interactions
        verify(userRepository, times(1)).getUserEntityByUsername(request.getUsername());
        verifyNoMoreInteractions(passwordEncoder, accessTokenEncoder);
    }

    @Test
    void loginUser_InvalidPassword_ThrowsInvalidCredentialsException() {
        // Arrange
        LogInUserRequest request = new LogInUserRequest("username", "wrongPassword");
        UserEntity userEntity = UserEntity.builder().userId(1L).username("username").password("password").build();

        when(userRepository.getUserEntityByUsername(request.getUsername())).thenReturn(userEntity);
        when(passwordEncoder.matches(request.getPassword(), userEntity.getPassword())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> logInUserUseCase.loginUser(request));

        // Verify interactions
        verify(userRepository, times(1)).getUserEntityByUsername(request.getUsername());
        verify(passwordEncoder, times(1)).matches(request.getPassword(), userEntity.getPassword());
        verifyNoMoreInteractions(accessTokenEncoder);
    }

}