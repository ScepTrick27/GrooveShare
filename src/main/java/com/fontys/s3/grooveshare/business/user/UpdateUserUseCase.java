package com.fontys.s3.grooveshare.business.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
