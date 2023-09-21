package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
public class UserConverter {

    private UserConverter(){

    }

public static User convert(UserEntity user){
return User.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .password(user.getPassword())
        .description(user.getDescription())
        .userGender(user.getUserGender())
        .build();
}
}
