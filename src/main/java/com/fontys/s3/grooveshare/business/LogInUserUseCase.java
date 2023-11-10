package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.LogInUserRequest;
import com.fontys.s3.grooveshare.business.dtos.LogInUserResponse;

public interface LogInUserUseCase {
    LogInUserResponse loginUser(LogInUserRequest request);
}
