package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.domain.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
