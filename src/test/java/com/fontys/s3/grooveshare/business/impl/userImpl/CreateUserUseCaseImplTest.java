package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserResponse;
import com.fontys.s3.grooveshare.business.impl.userImpl.CreateUserUseCaseImpl;
import com.fontys.s3.grooveshare.persistance.RoleRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.RoleEnum;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void createUserShouldCreateANewUser() {
        // Mock data
        CreateUserRequest request = CreateUserRequest.builder()
                .username("test1")
                .password("test1")
                .userGender(UserGenderEntity.MALE)
                .description("test1")
                .userRole(UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build())
                .build();

        UserRoleEntity mockedRole = UserRoleEntity.builder()
                .id(1L)
                .role(RoleEnum.USER)
                .build();

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .build();

        // Mocking behavior
        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(mockedRole);
        when(passwordEncoder.encode("test1")).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // Test the method
        CreateUserResponse actual = createUserUseCase.createUser(request);
        CreateUserResponse expected = CreateUserResponse.builder().userId(1L).build();

        // Verify the interactions and assertions
        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(passwordEncoder).encode("test1");
        verify(userRepository).save(any(UserEntity.class));
    }
}





