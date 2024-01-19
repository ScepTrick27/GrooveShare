package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.post.GetPostUseCase;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPostUseCaseImpl implements GetPostUseCase {
    private final PostRepository postRepository;

    @Override
    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId).map(PostConverter::convert);
    }
}
