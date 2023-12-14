package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.FollowRequest;
import com.fontys.s3.grooveshare.domain.User;

public interface FollowUseCase {
    void follow(FollowRequest request);
}
