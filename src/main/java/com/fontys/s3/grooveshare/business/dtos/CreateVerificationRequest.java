package com.fontys.s3.grooveshare.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVerificationRequest {
    private Long userId;
    private byte[] verificationPhoto;
}
