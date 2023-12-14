package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.FollowEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
    Optional<FollowEntity> findByFollowerAndFollowee(UserEntity follower, UserEntity followee);
    List<FollowEntity> findByFollowerUserId(Long followerId);
    List<FollowEntity> findByFolloweeUserId(Long followeeId);

    @Modifying
    @Query("DELETE FROM FollowEntity f WHERE f.followee = :followee")
    void deleteByFollowee(UserEntity followee);

    @Modifying
    @Query("DELETE FROM FollowEntity f WHERE f.follower = :follower")
    void deleteByFollower(UserEntity follower);

}
