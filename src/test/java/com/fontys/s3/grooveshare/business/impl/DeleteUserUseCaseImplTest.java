package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;


    @Test
    void deleteUserShouldDeleteUserById() {
        Long userIdToDelete = 1L;

        deleteUserUseCase.DeleteUser(userIdToDelete);

        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }
}