package com.fontys.s3.grooveshare.business.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserResponse;

public interface LogInUserUseCase {
    LogInUserResponse loginUser(LogInUserRequest request);
}
