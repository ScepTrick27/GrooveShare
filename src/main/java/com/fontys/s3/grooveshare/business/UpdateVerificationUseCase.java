package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.UpdateVerificationRequest;

public interface UpdateVerificationUseCase {
    void updateVerification(UpdateVerificationRequest request);
}
