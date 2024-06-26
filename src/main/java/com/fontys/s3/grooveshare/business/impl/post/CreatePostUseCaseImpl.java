package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.post.CreatePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.CreatePostResponse;
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
        private static final String ERROR = "does not exist";

    @Transactional
    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + request.getUserId() + ERROR);
        }

        Optional<GenreEntity> genre = genreRepository.findById(request.getGenreId());
        if (genre.isEmpty()) {
            throw new IllegalArgumentException("Genre with ID " + request.getGenreId() + ERROR);
        }

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
                    .genre(genre.orElse(null))
                    .build();
            return postRepository.save(newPost);
        } else {
            throw new IllegalStateException("User with ID " + request.getUserId() + ERROR);
        }
    }
}
