package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationResponse;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HasSentVerificationUseCaseImplTest {
    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private HasSentVerificationUseCaseImpl hasSentVerificationUseCase;

    @Test
    void hasSentVerification_ReturnsFalse_WhenNoVerificationFound() {
        // Arrange
        HasSentVerificationRequest request = new HasSentVerificationRequest(1L);
        when(verificationRepository.findTopByUserUserIdAndStatusIn(request.getUserId(),
                Arrays.asList(VerificationStatusEntity.PENDING, VerificationStatusEntity.ACCEPTED)))
                .thenReturn(Optional.empty());

        // Act
        HasSentVerificationResponse response = hasSentVerificationUseCase.hasSentVerification(request);

        // Assert
        assertFalse(response.isHasSentVerification());
        assertNull(response.getVerificationStatus());
        verify(verificationRepository, times(1)).findTopByUserUserIdAndStatusIn(
                request.getUserId(), Arrays.asList(VerificationStatusEntity.PENDING, VerificationStatusEntity.ACCEPTED));
    }
    @Test
    void hasSentVerification_ReturnsTrue_WhenVerificationFound() {
        // Arrange
        HasSentVerificationRequest request = new HasSentVerificationRequest(1L);
        VerificationEntity verificationEntity = new VerificationEntity(
                1L,
                null,
                new byte[]{},
                VerificationStatusEntity.PENDING
        );
        when(verificationRepository.findTopByUserUserIdAndStatusIn(request.getUserId(),
                Arrays.asList(VerificationStatusEntity.PENDING, VerificationStatusEntity.ACCEPTED)))
                .thenReturn(Optional.of(verificationEntity));

        // Act
        HasSentVerificationResponse response = hasSentVerificationUseCase.hasSentVerification(request);

        // Assert
        assertTrue(response.isHasSentVerification());
        assertEquals(VerificationStatusEntity.PENDING.toString(), response.getVerificationStatus());
        verify(verificationRepository, times(1)).findTopByUserUserIdAndStatusIn(
                request.getUserId(), Arrays.asList(VerificationStatusEntity.PENDING, VerificationStatusEntity.ACCEPTED));
    }
}