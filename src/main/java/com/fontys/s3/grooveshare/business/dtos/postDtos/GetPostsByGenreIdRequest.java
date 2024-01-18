package com.fontys.s3.grooveshare.business.dtos.postDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostsByGenreIdRequest {
    private Long genreId;
}
