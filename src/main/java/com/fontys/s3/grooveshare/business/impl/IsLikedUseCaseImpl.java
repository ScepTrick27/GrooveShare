package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.IsLikedUseCase;
import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IsLikedUseCaseImpl implements IsLikedUseCase {
    private final LikeRepository likeRepository;
    @Override
    public IsLikedResponse isLiked(LikePostRequest request) {
        IsLikedResponse response = new IsLikedResponse();
        response.setLiked(likeRepository.existsByUserUserIdAndPostPostId(request.getUserId(), request.getPostId()));
        return response;
    }
}
