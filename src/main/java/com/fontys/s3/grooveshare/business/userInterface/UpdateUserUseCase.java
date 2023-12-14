package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.business.dtos.userDtos.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
