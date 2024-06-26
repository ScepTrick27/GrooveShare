package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
public class UserConverter {

    private UserConverter(){

    }

public static User convert(UserEntity user){
    if (user == null) {
        return null;
    }
return User.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .password(user.getPassword())
        .description(user.getDescription())
        .userGender(user.getUserGender())
        .userRole(user.getUserRole())
        .photo(user.getPhoto())
        .isVerified(user.isVerified())
        .build();
}
}
