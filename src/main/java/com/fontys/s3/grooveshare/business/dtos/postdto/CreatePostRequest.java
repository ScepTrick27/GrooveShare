package com.fontys.s3.grooveshare.business.dtos.postdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    @NotBlank
    private String content;
    @NotNull
    private Long userId;

    private String trackId;
    @NotNull
    private Long genreId;
}
