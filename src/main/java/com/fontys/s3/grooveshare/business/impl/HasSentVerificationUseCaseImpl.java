package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.HasSentVerificationUseCase;
import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationResponse;
import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HasSentVerificationUseCaseImpl implements HasSentVerificationUseCase {
    private final VerificationRepository verificationRepository;

    @Override
    public HasSentVerificationResponse hasSentVerification(HasSentVerificationRequest request) {
        HasSentVerificationResponse response = new HasSentVerificationResponse();

        Optional<VerificationEntity> verification = verificationRepository.findTopByUserUserIdAndStatusIn(
                request.getUserId(), Arrays.asList(VerificationStatusEntity.PENDING, VerificationStatusEntity.ACCEPTED));

        response.setHasSentVerification(verification.isPresent());
        response.setVerificationStatus(verification.map(v -> v.getStatus().toString()).orElse(null));

        return response;
    }
}
