package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UpdateVerificationUseCase;
import com.fontys.s3.grooveshare.business.dtos.UpdateVerificationRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateVerificationUseCaseImplTest {
    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private UpdateVerificationUseCaseImpl updateVerificationUseCase;

    @Test
    void updateVerification_ValidVerificationId_Success() {
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setId(1L);
        UserEntity user = new UserEntity();
        verificationEntity.setUser(user);
        verificationEntity.setVerificationPhoto(new byte[0]);
        verificationEntity.setStatus(VerificationStatusEntity.PENDING);

        when(verificationRepository.findById(1L)).thenReturn(Optional.of(verificationEntity));

        UpdateVerificationRequest updateRequest = new UpdateVerificationRequest();
        updateRequest.setVerificationId(1L);
        updateRequest.setVerificationStatus("ACCEPTED");

        updateVerificationUseCase.updateVerification(updateRequest);

        verify(verificationRepository, times(1)).findById(1L);

        assertEquals(VerificationStatusEntity.ACCEPTED, verificationEntity.getStatus());
    }
    @Test
    void updateVerification_InvalidVerificationId_ThrowsException() {
        UpdateVerificationRequest request = new UpdateVerificationRequest(2L, "ACCEPTED");
        when(verificationRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(InvalidUserIdException.class, () -> updateVerificationUseCase.updateVerification(request));

        verify(verificationRepository, times(1)).findById(2L);
    }
}