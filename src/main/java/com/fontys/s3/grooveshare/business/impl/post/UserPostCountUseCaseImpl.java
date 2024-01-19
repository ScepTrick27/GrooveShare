package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.post.UserPostCountUseCase;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetUserPostCountResponse;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserPostCountUseCaseImpl implements UserPostCountUseCase {

    private final PostRepository postRepository;
    @Override
    public GetUserPostCountResponse getUserPostCount() {
        List<com.fontys.s3.grooveshare.domain.UserPostCount> getUserPostCount = postRepository.getUserPostCounts();

        final GetUserPostCountResponse response = new GetUserPostCountResponse();
        response.setUserPostCounts(getUserPostCount);
        return response;
    }
}
