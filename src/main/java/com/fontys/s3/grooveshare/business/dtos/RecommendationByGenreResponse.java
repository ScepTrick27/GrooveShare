package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationByGenreResponse {
    private List<Post> recommendedPosts;
}
