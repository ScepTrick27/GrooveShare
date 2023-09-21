package com.fontys.s3.grooveshare.domain;

import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    @Min(3)
    @Max(25)
    @NotBlank
    private String username;
    @Min(5)
    @Max(25)
    @NotBlank
    private String password;
    private String description;
    @NotNull
    private UserGenderEntity userGender;
}
