package com.fontys.s3.grooveshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long postId;
    private String content;
    private User user;
    private Long likes;
    private String trackId;
    private Genre genre;
}
