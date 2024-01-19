package com.fontys.s3.grooveshare.business.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.UpdatePostRequest;

public interface UpdatePostUseCase {
    void updatePost(UpdatePostRequest request);
}
