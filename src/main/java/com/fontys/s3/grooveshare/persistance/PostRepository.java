package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.domain.UserPostCount;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    void deleteByUser (UserEntity user);

    @Query("SELECT p FROM PostEntity p")
    Page<PostEntity> getAllPosts(Pageable pageable);

    @Query("SELECT new com.fontys.s3.grooveshare.domain.UserPostCount(p.user.userId, u.username, COUNT(p)) " +
            "FROM PostEntity p INNER JOIN p.user as u GROUP BY p.user.userId, u.username")
    List<UserPostCount> getUserPostCounts();

    @Query("SELECT p FROM PostEntity p " +
            "JOIN FETCH p.user u " +
            "JOIN FollowEntity f ON p.user.userId = f.followee.userId " +
            "WHERE f.follower.userId = :loggedInUserId")
    List<PostEntity> findPostsByLoggedInUserAndFollowers(@Param("loggedInUserId") Long loggedInUserId);

    @Query("SELECT COUNT(l) FROM LikeEntity l WHERE l.post.postId = :postId")
    Long countLikesForPost(Long postId);

    @Query("SELECT l.post FROM LikeEntity l WHERE l.user.userId = :userId")
    List<PostEntity> findLikedPostsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM PostEntity p WHERE p.genre.id = :genreId")
    List<PostEntity> findByGenreId(@Param("genreId") Long genreId);
}
