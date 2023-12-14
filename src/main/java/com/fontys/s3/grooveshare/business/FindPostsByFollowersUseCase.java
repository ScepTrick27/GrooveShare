package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.FindPostsByFollowersResponse;

public interface FindPostsByFollowersUseCase {
    FindPostsByFollowersResponse findPostsByLoggedInUserAndFollowers(Long userId);
}
