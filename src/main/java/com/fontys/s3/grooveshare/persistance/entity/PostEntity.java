package com.fontys.s3.grooveshare.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostEntity {

private Long postId;
private String content;
}
