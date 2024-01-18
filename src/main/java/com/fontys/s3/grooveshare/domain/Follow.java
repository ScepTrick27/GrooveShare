package com.fontys.s3.grooveshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    private Long id;
    private User follower;
    private User followee;
}
