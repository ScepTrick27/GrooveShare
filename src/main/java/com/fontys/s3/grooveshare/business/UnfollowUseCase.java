package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.UnfollowRequest;
import com.fontys.s3.grooveshare.domain.User;

public interface UnfollowUseCase {
    void unfollow(UnfollowRequest request);
}
