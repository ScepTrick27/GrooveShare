package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.GetAllPostsUseCase;
import com.fontys.s3.grooveshare.business.dtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPostsUseCaseImpl implements GetAllPostsUseCase {
    private final PostRepository postRepository;

    @Override
    public GetAllPostsResponse getPosts(GetAllPostsRequest request) {
        List<PostEntity> results = postRepository.findAll();

        final GetAllPostsResponse response = new GetAllPostsResponse();
        List<Post> posts = results
                .stream()
                .map(PostConverter::convert)
                .toList();
        response.setPosts(posts);

        return response;
    }
}
