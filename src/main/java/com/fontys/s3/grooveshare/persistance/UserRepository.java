package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> getUserById(Long userId);

    UserEntity save(UserEntity user);

    void deleteById(Long userId);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findById(long userId);

}
