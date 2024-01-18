package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.domain.Verification;

import java.util.Optional;

public interface GetVerificationByIdUseCase {
    Optional<Verification> getVerification(Long verificationId);
}
