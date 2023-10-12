package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.DTOs.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.DTOs.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
