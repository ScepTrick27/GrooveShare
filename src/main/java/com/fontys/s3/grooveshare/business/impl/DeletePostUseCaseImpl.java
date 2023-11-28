package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.DeletePostUseCase;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
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
        this.postRepository.deleteById(postId);
    }
}
