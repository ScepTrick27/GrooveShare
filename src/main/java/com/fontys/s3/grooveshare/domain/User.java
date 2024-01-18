package com.fontys.s3.grooveshare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String username;
    @JsonIgnore
    private String password;
    private String description;
    private UserGenderEntity userGender;
    private UserRoleEntity userRole;
    private byte[] photo;
    private boolean isVerified;
}
