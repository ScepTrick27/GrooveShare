package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u")
    List<UserEntity> getAllUsers();
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    UserEntity getUserEntityByUsername(String username);


}
