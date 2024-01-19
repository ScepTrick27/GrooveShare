package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.impl.userImpl.UserConverter;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;

public class VerificationConverter {
    public static Verification convert(VerificationEntity verification){
        User user = UserConverter.convert(verification.getUser());
        return Verification.builder()
                .id(verification.getId())
                .status(verification.getStatus())
                .user(user)
                .verificationPhoto(verification.getVerificationPhoto())
                .build();
    }
}
