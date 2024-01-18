package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetVerificationByIdUseCaseImplTest {
    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private GetVerificationByIdUseCaseImpl getVerificationByIdUseCase;

    @Test
    void getVerification_ReturnsEmptyOptional_WhenVerificationNotFound() {
        // Arrange
        Long nonExistentVerificationId = 1L;
        when(verificationRepository.findById(nonExistentVerificationId)).thenReturn(Optional.empty());

        // Act
        Optional<Verification> result = getVerificationByIdUseCase.getVerification(nonExistentVerificationId);

        // Assert
        assertTrue(result.isEmpty());
        verify(verificationRepository, times(1)).findById(nonExistentVerificationId);
    }

    @Test
    void getVerification_ReturnsVerification_WhenVerificationFound() {
        // Arrange
        Long existingVerificationId = 1L;
        VerificationEntity verificationEntity = new VerificationEntity(
                existingVerificationId,
                UserEntity.builder().userId(1L).username("User1").build(),
                new byte[]{},
                VerificationStatusEntity.PENDING
        );
        when(verificationRepository.findById(existingVerificationId)).thenReturn(Optional.of(verificationEntity));

        // Act
        Optional<Verification> result = getVerificationByIdUseCase.getVerification(existingVerificationId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(existingVerificationId, result.get().getId());
        assertEquals("User1", result.get().getUser().getUsername());
        assertEquals(VerificationStatusEntity.PENDING, result.get().getStatus());
        verify(verificationRepository, times(1)).findById(existingVerificationId);
    }
}