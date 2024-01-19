package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {
    Optional<VerificationEntity> findTopByUserUserIdAndStatusIn(Long userId, List<VerificationStatusEntity> statuses);
}
