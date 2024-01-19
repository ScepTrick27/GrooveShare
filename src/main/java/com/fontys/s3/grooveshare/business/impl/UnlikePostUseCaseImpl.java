package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UnlikePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.LikeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UnlikePostUseCaseImpl implements UnlikePostUseCase {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public LikePostResponse unlikePost(LikePostRequest request) {
        LikePostResponse response = new LikePostResponse();

        Optional<LikeEntity> likeOptional = likeRepository.findByUserUserIdAndPostPostId(request.getUserId(), request.getPostId());
        if (likeOptional.isPresent()) {
            LikeEntity like = likeOptional.get();
            likeRepository.delete(like);
            response.setSuccess(true);
        }

        return response;
    }
}
