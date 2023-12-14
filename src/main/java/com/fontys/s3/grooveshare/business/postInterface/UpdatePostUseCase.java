package com.fontys.s3.grooveshare.business.postInterface;

import com.fontys.s3.grooveshare.business.dtos.postDtos.UpdatePostRequest;

public interface UpdatePostUseCase {
    void updatePost(UpdatePostRequest request);
}
