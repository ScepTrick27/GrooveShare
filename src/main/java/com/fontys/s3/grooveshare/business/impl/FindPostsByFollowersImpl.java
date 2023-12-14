package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.FindPostsByFollowersUseCase;
import com.fontys.s3.grooveshare.business.dtos.FindPostsByFollowersRequest;
import com.fontys.s3.grooveshare.business.dtos.FindPostsByFollowersResponse;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindPostsByFollowersImpl implements FindPostsByFollowersUseCase {
private final PostRepository postRepository;
    public FindPostsByFollowersResponse findPostsByLoggedInUserAndFollowers(Long userId) {
        List<PostEntity> posts = postRepository.findPostsByLoggedInUserAndFollowers(userId);

        FindPostsByFollowersResponse response = new FindPostsByFollowersResponse();
        response.setPosts(posts);

        return response;
    }
}
