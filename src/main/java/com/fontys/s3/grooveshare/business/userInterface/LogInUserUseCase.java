package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.business.dtos.userDtos.LogInUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.LogInUserResponse;

public interface LogInUserUseCase {
    LogInUserResponse loginUser(LogInUserRequest request);
}
