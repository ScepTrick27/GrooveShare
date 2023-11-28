package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UpdatePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.UpdatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.UpdateUserRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatePostUseCaseImpl implements UpdatePostUseCase {
    private final PostRepository postRepository;
    @Override
    @Transactional
    public void updatePost(UpdatePostRequest request) {
        Optional<PostEntity> postOptional = postRepository.findById(request.getPostId());

        if (postOptional.isEmpty()) {
            throw new InvalidUserIdException("POST_ID_INVALID");
        }

    PostEntity post = postOptional.get();
    updateFields(request, post);

}

    private void updateFields(UpdatePostRequest request, PostEntity post){
        post.setContent(request.getContent());
    }
}
