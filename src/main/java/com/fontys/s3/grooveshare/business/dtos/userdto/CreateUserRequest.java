package com.fontys.s3.grooveshare.business.dtos.userdto;

import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String description;
    @NotNull
    private UserGenderEntity userGender;
    private UserRoleEntity userRole;
    private byte[] photo;
}