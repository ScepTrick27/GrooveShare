package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerificationConverterTest {
    @Test
    void convert_ShouldConvertVerificationEntityToVerification() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserId()).thenReturn(1L);
        // Set up other userEntity fields...

        VerificationEntity verificationEntity = mock(VerificationEntity.class);
        when(verificationEntity.getId()).thenReturn(1L);
        when(verificationEntity.getStatus()).thenReturn(VerificationStatusEntity.PENDING);
        when(verificationEntity.getUser()).thenReturn(userEntity);
        // Set up other verificationEntity fields...

        // Act
        Verification convertedVerification = VerificationConverter.convert(verificationEntity);

        // Assert
        assertEquals(verificationEntity.getId(), convertedVerification.getId());
        assertEquals(verificationEntity.getStatus(), convertedVerification.getStatus());
        assertEquals(userEntity.getUserId(), convertedVerification.getUser().getUserId());
        // Assert other fields...
    }
}