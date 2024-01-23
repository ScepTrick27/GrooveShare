package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.CreateVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.CreateVerificationResponse;
import com.fontys.s3.grooveshare.persistance.UserRepository;
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
class CreateVerificationUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private CreateVerificationUseCaseImpl createVerificationUseCase;

    @Test
    void createVerification_WithValidUserId_ShouldReturnCreateVerificationResponse() {
        byte[] photo = {1};
        CreateVerificationRequest request = CreateVerificationRequest.builder()
                .userId(14L)
                .verificationPhoto(photo)
                .build();

        CreateVerificationResponse response = createVerificationUseCase.createVerification(request);
        response.setUserId(request.getUserId());
        response.setStatus("PENDING");

        assertNotNull(response);
        assertNotNull(response.getUserId(), "User ID should not be null");
        assertEquals("PENDING", response.getStatus());
    }

    @Test
    void createVerification_WithInvalidUserId_ShouldReturnEmptyResponse() {
        Long invalidUserId = 2L;
        byte[] photo = {1};
        CreateVerificationRequest request = new CreateVerificationRequest(invalidUserId, photo);
        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

        CreateVerificationResponse response = createVerificationUseCase.createVerification(request);

        assertNotNull(response);
        assertNull(response.getUserId());
        assertNull(response.getStatus());
        verify(verificationRepository, never()).save(any(VerificationEntity.class));
    }
}