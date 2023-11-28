package com.fontys.s3.grooveshare.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "s3_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
private Long postId;
    @Length(min =2, max = 250)
    @Column(name = "content")
private String content;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
private UserEntity user;
}
