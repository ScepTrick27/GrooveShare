package com.fontys.s3.grooveshare.business;

public interface IsFollowingUseCase {
    boolean isFollowing(Long followerId, Long followeeId);
}
