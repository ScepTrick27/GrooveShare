package com.fontys.s3.grooveshare.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "s3_verification")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "photo")
    private byte[] verificationPhoto;
    @Enumerated(EnumType.STRING)
    private VerificationStatusEntity status;

}
