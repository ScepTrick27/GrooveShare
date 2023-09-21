package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.domain.GetAllUsersRequest;
import com.fontys.s3.grooveshare.domain.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
