package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.CreatePostResponse;

public interface CreatePostUseCase {
    CreatePostResponse createPost(CreatePostRequest request);
}
