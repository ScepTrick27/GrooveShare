package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.GetFilteredUsersResponse;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFilteredUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetFilteredUsersUseCaseImpl getFilteredUsersUseCase;

    @Test
    public void testFilteredSearch() {
        String username = "testUsername";
        List<UserEntity> mockUserEntities = Collections.singletonList(new UserEntity());
        when(userRepository.filteredSearch(username)).thenReturn(mockUserEntities);

        GetFilteredUsersResponse response = getFilteredUsersUseCase.filteredSearch(username);

        List<User> expectedUsers = mockUserEntities.stream()
                .map(UserConverter::convert)
                .toList();
        assertEquals(expectedUsers, response.getUsers());
    }
}