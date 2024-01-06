package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;

public interface UnlikePostUseCase {
    LikePostResponse unlikePost(LikePostRequest request);
}
