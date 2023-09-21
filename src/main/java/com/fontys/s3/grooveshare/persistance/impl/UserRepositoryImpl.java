package com.fontys.s3.grooveshare.persistance.impl;

import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static long userId = 1;

    private List<UserEntity> users;

    public UserRepositoryImpl(){
        this.users = new ArrayList<>();
    }

    @Override
    public Optional<UserEntity> getUserById(Long userId) {
        return this.users.stream()
                .filter(userEntity -> userEntity.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getUserId() == null) {
            user.setUserId(userId);
            userId++;
            this.users.add(user);
        }
        return user;
    }

    @Override
    public void deleteById(Long userId) {
        this.users.removeIf(userEntity -> userEntity.getUserId().equals(userId));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return Collections.unmodifiableList(this.users);
    }

    @Override
    public Optional<UserEntity> findById(long userId) {
        return this.users.stream()
                .filter(userEntity -> userEntity.getUserId().equals(userId))
                .findFirst();
    }
}
