package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.VerifyUserRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifiyUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private VerifiyUserUseCaseImpl verifyUserUseCase;

    @Test
    void verifyUser_ValidUserIdAndAcceptedStatus_VerificationSuccess() {
        // Arrange
        long userId = 1L;
        VerifyUserRequest request = new VerifyUserRequest(userId, "ACCEPTED");
        UserEntity userEntity = UserEntity.builder().userId(userId).isVerified(false).build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        verifyUserUseCase.verifyUser(request);

        // Assert
        assertTrue(userEntity.isVerified());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void verifyUser_ValidUserIdAndRejectedStatus_VerificationSuccess() {
        // Arrange
        long userId = 1L;
        VerifyUserRequest request = new VerifyUserRequest(userId, "DECLINED");
        UserEntity userEntity = UserEntity.builder().userId(userId).isVerified(false).build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        verifyUserUseCase.verifyUser(request);

        // Assert
        assertFalse(userEntity.isVerified());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void verifyUser_InvalidUserId_ThrowsException() {
        // Arrange
        long userId = 1L;
        VerifyUserRequest request = new VerifyUserRequest(userId, "ACCEPTED");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidUserIdException.class, () -> verifyUserUseCase.verifyUser(request));
        verify(userRepository, times(1)).findById(userId);
    }
}