package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.domain.User;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u")
    Page<UserEntity> getAllUsers(Pageable pageable);
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    UserEntity getUserEntityByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE (:username is null or u.username like %:username%)")
    List<UserEntity> filteredSearch(String username);

}
