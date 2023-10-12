package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.DTOs.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
