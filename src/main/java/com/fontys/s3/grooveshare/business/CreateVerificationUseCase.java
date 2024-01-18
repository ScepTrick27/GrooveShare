package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.CreateVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.CreateVerificationResponse;

public interface CreateVerificationUseCase {
    CreateVerificationResponse createVerification(CreateVerificationRequest request);
}
