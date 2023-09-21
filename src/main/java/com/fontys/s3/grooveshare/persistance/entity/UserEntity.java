package com.fontys.s3.grooveshare.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {

    private Long userId;
    private String username;
    private String password;
    private String description;
    private UserGenderEntity userGender;
}
