package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllPostsResponse;

public interface GetAllPostsUseCase {
    GetAllPostsResponse getPosts(GetAllPostsRequest request);
}
