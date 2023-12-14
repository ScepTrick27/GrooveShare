package com.fontys.s3.grooveshare.business.dtos.postDtos;

import com.fontys.s3.grooveshare.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPostCountResponse {
    private List<com.fontys.s3.grooveshare.domain.UserPostCount> userPostCounts;
}
