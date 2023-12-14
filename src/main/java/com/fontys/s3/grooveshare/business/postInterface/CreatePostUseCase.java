package com.fontys.s3.grooveshare.business.postInterface;

import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostResponse;

public interface CreatePostUseCase {
    CreatePostResponse createPost(CreatePostRequest request);
}
