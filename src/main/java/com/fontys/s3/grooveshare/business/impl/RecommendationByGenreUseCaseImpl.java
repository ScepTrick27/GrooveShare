package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.RecommendationByGenreUseCase;
import com.fontys.s3.grooveshare.business.dtos.RecommendationByGenreRequest;
import com.fontys.s3.grooveshare.business.dtos.RecommendationByGenreResponse;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.business.impl.postImpl.PostConverter;
import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.LikeRepository;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommendationByGenreUseCaseImpl implements RecommendationByGenreUseCase {
    private PostRepository postRepository;

    public RecommendationByGenreResponse getRecommendedPosts(Long userId) {

        List<PostEntity> likedPosts = postRepository.findLikedPostsByUserId(userId);

        List<Genre> likedGenres = likedPosts.stream()
                .map(postEntity -> GenreConvertor.convert(postEntity.getGenre()))
                .collect(Collectors.toList());

        List<Genre> topGenres = calculateTopGenres(likedGenres);

        List<PostEntity> results = postRepository.findAll();

        List<Post> posts = results
                .stream()
                .map(postEntity -> {
                    Post post = PostConverter.convert(postEntity);
                    post.setLikes(postRepository.countLikesForPost(postEntity.getPostId()));
                    return post;
                })
                .toList();

        List<Post> recommendedPosts = posts.stream()
                .filter(post -> topGenres.contains(post.getGenre()))
                .sorted(Comparator.comparingLong(Post::getLikes).reversed())
                .collect(Collectors.toList());

        return new RecommendationByGenreResponse(recommendedPosts);
    }

    private List<Genre> calculateTopGenres(List<Genre> likedGenres) {
        Map<Genre, Long> genreCounts = likedGenres.stream()
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        return genreCounts.entrySet().stream()
                .sorted(Map.Entry.<Genre, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
