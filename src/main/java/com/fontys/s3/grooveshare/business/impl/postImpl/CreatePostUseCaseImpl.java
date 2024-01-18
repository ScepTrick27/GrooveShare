package com.fontys.s3.grooveshare.business.impl.postImpl;

import com.fontys.s3.grooveshare.business.postInterface.CreatePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostResponse;
import com.fontys.s3.grooveshare.persistance.GenreRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
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
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        // Check if the user exists
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + request.getUserId() + " does not exist");
        }

        // Check if the genre exists
        Optional<GenreEntity> genre = genreRepository.findById(request.getGenreId());
        if (genre.isEmpty()) {
            throw new IllegalArgumentException("Genre with ID " + request.getGenreId() + " does not exist");
        }

        // Create and save the post
        PostEntity savedPost = saveNewPost(request);

        return CreatePostResponse.builder()
                .postId(savedPost.getPostId())
                .content(savedPost.getContent())
                .trackId(savedPost.getTrackId())
                .build();
    }

    private PostEntity saveNewPost(CreatePostRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        Optional<GenreEntity> genre = genreRepository.findById(request.getGenreId());

        if (user.isPresent()) {
            PostEntity newPost = PostEntity.builder()
                    .content(request.getContent())
                    .user(user.get())
                    .trackId(request.getTrackId())
                    .genre(genre.get())
                    .build();
            return postRepository.save(newPost);
        } else {
            // This should not be reached due to the earlier check
            throw new IllegalStateException("User with ID " + request.getUserId() + " does not exist");
        }
    }
}
