package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.impl.GenreConvertor;
import com.fontys.s3.grooveshare.business.impl.user.UserConverter;
import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;

public class PostConverter {

    private PostConverter(){

    }

    public static Post convert(PostEntity post){
        User user = UserConverter.convert(post.getUser());
        Genre genre = GenreConvertor.convert(post.getGenre());
    return Post.builder()
            .postId(post.getPostId())
            .content(post.getContent())
            .user(user)
            .trackId(post.getTrackId())
            .genre(genre)
            .build();
    }
}
