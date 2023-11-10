package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.CreateUserRequest;
import com.fontys.s3.grooveshare.business.dtos.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}

