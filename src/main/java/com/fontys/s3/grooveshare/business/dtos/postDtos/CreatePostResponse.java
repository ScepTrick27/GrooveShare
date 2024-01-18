package com.fontys.s3.grooveshare.business.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostResponse {
    private Long postId;
    private String content;
    private String trackId;
    private Long genreId;
}
