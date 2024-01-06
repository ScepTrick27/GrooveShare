package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;

public interface IsLikedUseCase {
    IsLikedResponse isLiked(LikePostRequest request);
}
