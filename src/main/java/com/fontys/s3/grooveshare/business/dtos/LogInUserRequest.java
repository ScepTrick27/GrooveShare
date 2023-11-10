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
public class LogInUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
