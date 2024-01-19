package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UpdateVerificationUseCase;
import com.fontys.s3.grooveshare.business.dtos.UpdateVerificationRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UpdateVerificationUseCaseImpl implements UpdateVerificationUseCase {
    private final VerificationRepository verificationRepository;
    @Override
    @Transactional
    public void updateVerification(UpdateVerificationRequest request) {
        Optional<VerificationEntity> verificationOptional = verificationRepository.findById(request.getVerificationId());

        if (verificationOptional.isEmpty()) {
            throw new InvalidUserIdException("VERIFICATION_ID_INVALID");
        }

        VerificationEntity verification = verificationOptional.get();
        updateFields(request, verification);

    }

    private void updateFields(UpdateVerificationRequest request, VerificationEntity verification){
        verification.setStatus(VerificationStatusEntity.valueOf(request.getVerificationStatus()));

    }
}
