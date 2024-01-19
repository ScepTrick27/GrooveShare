package com.fontys.s3.grooveshare.business.post;

import com.fontys.s3.grooveshare.domain.Post;

import java.util.Optional;

public interface GetPostUseCase {
    Optional<Post> getPost(Long postId);
}
