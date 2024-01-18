package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.VerifyUserRequest;

public interface VerifyUserUseCase {
    void verifyUser(VerifyUserRequest request);
}
