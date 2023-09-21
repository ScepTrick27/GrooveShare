package com.fontys.s3.grooveshare.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllUsersRequest {
    private long userId;
}
