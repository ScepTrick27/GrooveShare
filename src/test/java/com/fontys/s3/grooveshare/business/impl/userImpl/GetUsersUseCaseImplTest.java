package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersResponse;
import com.fontys.s3.grooveshare.business.impl.userImpl.GetUsersUseCaseImpl;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void getUsersShouldReturnAllUsers() {
        List<UserEntity> userEntities = List.of(
                UserEntity.builder().userId(1L).username("user1").build(),
                UserEntity.builder().userId(2L).username("user2").build()
        );

        PageImpl<UserEntity> page = new PageImpl<>(userEntities);

        when(userRepository.getAllUsers(any(PageRequest.class))).thenReturn(page);

        GetAllUsersRequest request = GetAllUsersRequest.builder().page(1).size(10).build();

        GetAllUsersResponse response = getUsersUseCase.getUsers(request);

        verify(userRepository, times(1)).getAllUsers(any(PageRequest.class));

        assertEquals(2, response.getUsers().size());
        assertEquals("user1", response.getUsers().get(0).getUsername());
        assertEquals("user2", response.getUsers().get(1).getUsername());
    }
}