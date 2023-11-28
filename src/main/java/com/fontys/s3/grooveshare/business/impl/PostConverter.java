package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;

public class PostConverter {

    private PostConverter(){

    }

    public static Post convert(PostEntity post){
        User user = UserConverter.convert(post.getUser());
    return Post.builder()
            .postId(post.getPostId())
            .content(post.getContent())
            .user(user)
            .build();
    }
}
