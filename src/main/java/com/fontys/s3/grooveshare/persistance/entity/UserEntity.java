package com.fontys.s3.grooveshare.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "s3_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @Length(max = 500)
    private String password;
    @Column(name = "description")
    @Length(max = 500)
    private String description;
    @Column(name = "user_gender_id")
    private UserGenderEntity userGender;
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoleEntity userRole;
}
