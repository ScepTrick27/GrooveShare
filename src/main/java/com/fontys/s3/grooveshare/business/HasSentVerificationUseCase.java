package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.HasSentVerificationResponse;

public interface HasSentVerificationUseCase {
    HasSentVerificationResponse hasSentVerification(HasSentVerificationRequest request);
}
