package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.domain.CreateUserRequest;
import com.fontys.s3.grooveshare.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
