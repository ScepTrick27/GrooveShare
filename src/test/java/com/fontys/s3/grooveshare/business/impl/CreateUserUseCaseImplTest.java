package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.CreateUserRequest;
import com.fontys.s3.grooveshare.domain.CreateUserResponse;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    /*@Test
    void CreateUserShouldCreateANewUser(){

        CreateUserRequest request = CreateUserRequest.builder()
                .username("test1")
                .password("test1")
                .userGender(UserGenderEntity.Female)
                .description("test1")
                .build();

        UserEntity userToSave = UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .userGender(request.getUserGender())
                .description(request.getDescription())
                .build();

        when(userRepository.save(userToSave)).thenReturn(UserEntity.builder().userId(1L).build());


        CreateUserResponse actual = createUserUseCase.createUser(request);
        CreateUserResponse expected = CreateUserResponse.builder().userId(1L).build();

        assertEquals(expected, actual);

        verify(userRepository).save(userToSave);
    }*/

}