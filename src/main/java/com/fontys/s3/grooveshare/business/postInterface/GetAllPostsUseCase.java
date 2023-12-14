package com.fontys.s3.grooveshare.business.postInterface;

import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;

public interface GetAllPostsUseCase {
    GetAllPostsResponse getPosts(GetAllPostsRequest request);
}
