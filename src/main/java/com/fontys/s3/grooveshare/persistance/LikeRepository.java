package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByUserUserIdAndPostPostId(Long userId, Long postId);

    Optional<LikeEntity> findByUserUserIdAndPostPostId(Long userId, Long postId);
}
