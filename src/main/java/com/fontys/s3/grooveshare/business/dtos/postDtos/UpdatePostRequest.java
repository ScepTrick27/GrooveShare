package com.fontys.s3.grooveshare.business.dtos.postDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    private Long postId;
    @NotBlank
    private String content;
}
