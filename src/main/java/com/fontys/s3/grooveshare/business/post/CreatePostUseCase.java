package com.fontys.s3.grooveshare.business.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostResponse;

public interface CreatePostUseCase {
    CreatePostResponse createPost(CreatePostRequest request);
}
