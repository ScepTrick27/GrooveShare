package com.fontys.s3.grooveshare.business.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.GetFilteredUsersResponse;

public interface GetFilteredUsersUseCase {
    GetFilteredUsersResponse filteredSearch(String username);
}
