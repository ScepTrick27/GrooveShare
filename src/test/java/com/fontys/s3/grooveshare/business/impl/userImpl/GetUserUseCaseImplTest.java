package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.impl.userImpl.GetUserUseCaseImpl;
import com.fontys.s3.grooveshare.domain.User;
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
class GetUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUserShouldReturnUserById() {
        UserEntity userEntity = UserEntity.builder().userId(1L).username("user1").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        Optional<User> userOptional = getUserUseCase.getUser(1L);

        verify(userRepository, times(1)).findById(1L);

        assertEquals("user1", userOptional.get().getUsername());
    }

    @Test
    void getUserShouldReturnEmptyOptionalForNonExistentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> userOptional = getUserUseCase.getUser(1L);

        verify(userRepository, times(1)).findById(1L);

        assertEquals(Optional.empty(), userOptional);
    }
}