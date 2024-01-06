package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.LikePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import com.fontys.s3.grooveshare.domain.Like;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.LikeEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikePostUseCaseImpl implements LikePostUseCase {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public LikePostResponse likePost(LikePostRequest request) {
        LikePostResponse response = new LikePostResponse();

        if (!likeRepository.existsByUserUserIdAndPostPostId(request.getUserId(), request.getPostId())) {
            UserEntity user = userRepository.getById(request.getUserId());
            PostEntity post = postRepository.getById(request.getPostId());

            if (user != null && post != null) {
                LikeEntity like = new LikeEntity();
                like.setUser(user);
                like.setPost(post);
                likeRepository.save(like);
                response.setSuccess(true);
            }
        }

        return response;
    }
}
