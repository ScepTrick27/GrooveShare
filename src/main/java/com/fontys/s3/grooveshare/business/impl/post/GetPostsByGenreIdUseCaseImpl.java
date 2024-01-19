package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdResponse;
import com.fontys.s3.grooveshare.business.post.GetPostsByGenreIdUseCase;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPostsByGenreIdUseCaseImpl implements GetPostsByGenreIdUseCase {
    private final PostRepository postRepository;

    @Override
    public GetPostsByGenreIdResponse getPostsByGenreId(GetPostsByGenreIdRequest request) {
        List<PostEntity> results;

        results = postRepository.findByGenreId(request.getGenreId());

        final GetPostsByGenreIdResponse response = new GetPostsByGenreIdResponse();
        List<Post> posts = results
                .stream()
                .map(postEntity -> {
                    Post post = PostConverter.convert(postEntity);
                    post.setLikes(postRepository.countLikesForPost(postEntity.getPostId()));
                    return post;
                })
                .toList();
        response.setPosts(posts);

        return response;
    }
}
