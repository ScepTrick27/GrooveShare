package com.fontys.s3.grooveshare.domain;

import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Verification {
    private Long id;
    private User user;
    private byte[] verificationPhoto;
    private VerificationStatusEntity status;
}
