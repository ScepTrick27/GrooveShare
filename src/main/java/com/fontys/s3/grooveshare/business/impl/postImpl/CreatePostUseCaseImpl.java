package com.fontys.s3.grooveshare.business.impl.postImpl;

import com.fontys.s3.grooveshare.business.postInterface.CreatePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostResponse;
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
public class CreatePostUseCaseImpl implements CreatePostUseCase {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        PostEntity savedPost = saveNewPost(request);

        if (savedPost != null) {
            return CreatePostResponse.builder()
                    .postId(savedPost.getPostId())
                    .content(savedPost.getContent())
                    .build();
        } else {
            return CreatePostResponse.builder()
                    .build();
        }
    }

    private PostEntity saveNewPost(CreatePostRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());

        if (user.isPresent()) {
            PostEntity newPost = PostEntity.builder()
                    .content(request.getContent())
                    .user(user.get())
                    .build();
            return postRepository.save(newPost);
        } else {
            return null;
        }
    }
}
