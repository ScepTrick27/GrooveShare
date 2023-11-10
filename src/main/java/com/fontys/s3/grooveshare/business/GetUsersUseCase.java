package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
