package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.RoleEnum;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(RoleEnum roleEnum);
}
