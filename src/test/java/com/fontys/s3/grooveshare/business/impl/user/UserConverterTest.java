package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {
    @Test
    void convert_ShouldConvertUserEntityToUser() {
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserId()).thenReturn(1L);
        when(userEntity.getUsername()).thenReturn("TestUser");
        when(userEntity.getPassword()).thenReturn("TestPassword");
        when(userEntity.getDescription()).thenReturn("TestDescription");
        when(userEntity.getUserGender()).thenReturn(UserGenderEntity.FEMALE);
        when(userEntity.getUserRole()).thenReturn(UserRoleEntity.builder().build());
        when(userEntity.getPhoto()).thenReturn(new byte[0]);
        when(userEntity.isVerified()).thenReturn(true);

        User convertedUser = UserConverter.convert(userEntity);

        assertEquals(userEntity.getUserId(), convertedUser.getUserId());
        assertEquals(userEntity.getUsername(), convertedUser.getUsername());
        assertEquals(userEntity.getPassword(), convertedUser.getPassword());
        assertEquals(userEntity.getDescription(), convertedUser.getDescription());
        assertEquals(userEntity.getUserGender(), convertedUser.getUserGender());
        assertEquals(userEntity.getUserRole(), convertedUser.getUserRole());
        assertEquals(userEntity.getPhoto(), convertedUser.getPhoto());
        assertEquals(userEntity.isVerified(), convertedUser.isVerified());
    }

    @Test
    void convert_WithNullUserEntity_ShouldReturnNull() {
        UserEntity userEntity = null;

        User convertedUser = UserConverter.convert(userEntity);

        assertNull(convertedUser);
    }
}