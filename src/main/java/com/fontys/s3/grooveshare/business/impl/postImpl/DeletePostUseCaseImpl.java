package com.fontys.s3.grooveshare.business.impl.postImpl;

import com.fontys.s3.grooveshare.business.postInterface.DeletePostUseCase;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePostUseCaseImpl implements DeletePostUseCase {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void deletePost(Long postId) {
        if (postId == null || postId == -1) {
            throw new IllegalArgumentException("postId cannot be null");
        }
        this.postRepository.deleteById(postId);
    }
}
