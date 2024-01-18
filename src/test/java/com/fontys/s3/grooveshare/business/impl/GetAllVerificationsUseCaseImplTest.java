package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsResponse;
import com.fontys.s3.grooveshare.domain.User;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllVerificationsUseCaseImplTest {
    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private GetAllVerificationsUseCaseImpl getAllVerificationsUseCase;

    @Test
    void getAllVerifications_ReturnsEmptyList_WhenNoVerificationsInRepository() {
        // Arrange
        when(verificationRepository.findAll()).thenReturn(List.of());

        // Act
        GetAllVerificationsResponse response = getAllVerificationsUseCase.getAllVerifications(new GetAllVerificationsRequest());

        // Assert
        assertTrue(response.getVerificationList().isEmpty());
        verify(verificationRepository, times(1)).findAll();
    }

    @Test
    void getAllVerifications_ReturnsVerifications_WhenVerificationsInRepository() {
        // Arrange
        byte[] photo = {1};
        UserEntity user1 = UserEntity.builder().userId(1L).username("User1").build();
        UserEntity user2 = UserEntity.builder().userId(2L).username("User2").build();
        List<VerificationEntity> verificationEntities = Arrays.asList(
                new VerificationEntity(1L, user1, photo, VerificationStatusEntity.PENDING),
                new VerificationEntity(2L, user2, photo, VerificationStatusEntity.PENDING)
        );
        when(verificationRepository.findAll()).thenReturn(verificationEntities);

        // Act
        GetAllVerificationsResponse response = getAllVerificationsUseCase.getAllVerifications(new GetAllVerificationsRequest());

        // Assert
        List<Verification> actualVerifications = response.getVerificationList();
        assertEquals(2, actualVerifications.size());

        // Verify verification details for the first entity
        assertEquals(1L, actualVerifications.get(0).getId());
        assertEquals("User1", actualVerifications.get(0).getUser().getUsername());
        assertArrayEquals(photo, actualVerifications.get(0).getVerificationPhoto());
        assertEquals(VerificationStatusEntity.PENDING, actualVerifications.get(0).getStatus());

        // Verify verification details for the second entity
        assertEquals(2L, actualVerifications.get(1).getId());
        assertEquals("User2", actualVerifications.get(1).getUser().getUsername());
        assertArrayEquals(photo, actualVerifications.get(1).getVerificationPhoto());
        assertEquals(VerificationStatusEntity.PENDING, actualVerifications.get(1).getStatus());

        verify(verificationRepository, times(1)).findAll();
    }
    }