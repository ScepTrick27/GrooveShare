package com.fontys.s3.grooveshare.business.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInUserResponse {
    private String accessToken;
}
