package com.fontys.s3.grooveshare.business.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPostsRequest {
    private Long postId;
}
