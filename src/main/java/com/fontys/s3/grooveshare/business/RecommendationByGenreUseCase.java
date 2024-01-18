package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.RecommendationByGenreRequest;
import com.fontys.s3.grooveshare.business.dtos.RecommendationByGenreResponse;

public interface RecommendationByGenreUseCase {
    RecommendationByGenreResponse getRecommendedPosts(Long userId);
}
