package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.DTOs.CreateUserRequest;
import com.fontys.s3.grooveshare.business.DTOs.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
