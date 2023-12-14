package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}

