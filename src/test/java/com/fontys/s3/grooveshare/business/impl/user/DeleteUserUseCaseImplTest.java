package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.FollowRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;


    @Test
    void deleteUserShouldDeleteUserById() {
        Long userIdToDelete = 1L;

        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userIdToDelete)).thenReturn(Optional.of(userEntity));

        deleteUserUseCase.deleteUser(userIdToDelete);

        verify(userRepository, times(1)).deleteById(userIdToDelete);

        verify(followRepository, times(1)).deleteByFollowee(userEntity);
        verify(followRepository, times(1)).deleteByFollower(userEntity);
        verify(postRepository, times(1)).deleteByUser(userEntity);
    }

    @Test
    void deleteUserShouldThrowInvalidUserIdExceptionForNonexistentUser() {
        Long userIdToDelete = 1L;

        when(userRepository.findById(userIdToDelete)).thenReturn(Optional.empty());

        assertThrows(InvalidUserIdException.class, () -> deleteUserUseCase.deleteUser(userIdToDelete));

        verify(userRepository, never()).deleteById(userIdToDelete);
        verify(followRepository, never()).deleteByFollowee(any());
        verify(followRepository, never()).deleteByFollower(any());
        verify(postRepository, never()).deleteByUser(any());
    }
    }