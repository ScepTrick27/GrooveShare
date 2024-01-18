package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVerificationRequest {
    private Long verificationId;
    @NotBlank
    private String verificationStatus;
}
