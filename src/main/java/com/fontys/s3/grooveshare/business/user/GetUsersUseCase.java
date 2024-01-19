package com.fontys.s3.grooveshare.business.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.GetAllUsersRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
