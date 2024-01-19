package com.fontys.s3.grooveshare.business.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsResponse;

public interface GetAllPostsUseCase {
    GetAllPostsResponse getPosts(GetAllPostsRequest request);
}
