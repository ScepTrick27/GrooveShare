package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.post.GetAllPostsUseCase;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsResponse;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GetPostsUseCaseImpl implements GetAllPostsUseCase {
    private final PostRepository postRepository;

    @Override
    public GetAllPostsResponse getPosts(GetAllPostsRequest request) {
        if (request.getSize() < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        }

        Page<PostEntity> results = postRepository.getAllPosts(PageRequest.of(request.getPage(), request.getSize(), Sort.by("postId").descending()));

        final GetAllPostsResponse response = new GetAllPostsResponse();
        List<Post> posts = results
                .stream()
                .map(postEntity -> {
                    Post post = PostConverter.convert(postEntity);
                    post.setLikes(postRepository.countLikesForPost(postEntity.getPostId()));
                    return post;
                })
                .toList();
        response.setPosts(posts);
        response.setTotalPages(results.getTotalPages());
        response.setTotalPosts(results.getTotalElements());

        return response;
    }
}
