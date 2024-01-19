package com.fontys.s3.grooveshare.business.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.CreateUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}

