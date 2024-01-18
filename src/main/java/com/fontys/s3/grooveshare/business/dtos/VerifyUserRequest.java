package com.fontys.s3.grooveshare.business.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserRequest {
    private Long userId;
    @NotBlank
    private String verificationStatus;
}
