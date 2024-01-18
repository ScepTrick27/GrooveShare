package com.fontys.s3.grooveshare.business.dtos.postDtos;

import com.fontys.s3.grooveshare.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostsByGenreIdResponse {
    List<Post> posts;
}
