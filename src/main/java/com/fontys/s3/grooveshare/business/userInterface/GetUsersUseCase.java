package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
