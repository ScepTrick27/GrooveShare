package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.exception.UserException;
import com.fontys.s3.grooveshare.domain.UpdateUserRequest;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void updateUserShouldUpdateUserFields() {
        UserEntity userEntity = UserEntity.builder()
                .userId(1L)
                .username("user1")
                .password("password1")
                .description("description1")
                .userGender(UserGenderEntity.Female)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        UpdateUserRequest updateRequest = UpdateUserRequest.builder()
                .userId(1L)
                .username("updatedUser")
                .password("updatedPassword")
                .description("updatedDescription")
                .userGender(UserGenderEntity.Male)
                .build();

        updateUserUseCase.updateUser(updateRequest);

        verify(userRepository, times(1)).findById(1L);

        assertEquals("updatedUser", userEntity.getUsername());
        assertEquals("updatedPassword", userEntity.getPassword());
        assertEquals("updatedDescription", userEntity.getDescription());
    }

    @Test
    void updateUserShouldThrowUserExceptionForInvalidUserId() {
        String expectedMessage = "USER_ID_INVALID";
        long userId = 1L;
        UpdateUserRequest request = UpdateUserRequest.builder()
                .userId(userId)
                .username("newUsername")
                .password("newPassword")
                .userGender(UserGenderEntity.Female)
                .description("newDescription")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserException userException = assertThrows(UserException.class, () -> updateUserUseCase.updateUser(request));

        assertTrue(userException.getMessage().contains(expectedMessage));

        verify(userRepository).findById(userId);
    }
}