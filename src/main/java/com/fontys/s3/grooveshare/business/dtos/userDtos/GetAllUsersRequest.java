package com.fontys.s3.grooveshare.business.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersRequest {
    private long userId;
    private int page;
    private int size;
}