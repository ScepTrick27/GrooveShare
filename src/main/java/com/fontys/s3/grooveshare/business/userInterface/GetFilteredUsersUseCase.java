package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.business.dtos.userDtos.GetFilteredUsersResponse;

public interface GetFilteredUsersUseCase {
    GetFilteredUsersResponse filteredSearch(String username);
}
