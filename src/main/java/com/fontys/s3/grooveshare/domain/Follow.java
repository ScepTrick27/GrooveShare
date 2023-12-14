package com.fontys.s3.grooveshare.domain;

import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
