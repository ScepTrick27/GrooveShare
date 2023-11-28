package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.UpdatePostRequest;

public interface UpdatePostUseCase {
    void updatePost(UpdatePostRequest request);
}
