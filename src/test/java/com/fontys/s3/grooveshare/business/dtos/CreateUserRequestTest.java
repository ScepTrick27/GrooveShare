package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserRequest;
import com.fontys.s3.grooveshare.persistance.entity.UserGenderEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testNoArgsConstructor() {
        CreateUserRequest request = new CreateUserRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
    }

    @Test
    public void testAllArgsConstructor() {
        CreateUserRequest request = new CreateUserRequest(
                "testUser", "testPassword", "Test Description", UserGenderEntity.MALE, UserRoleEntity.builder().build());

        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals("testUser", request.getUsername(), "username should be set");
        assertEquals("testPassword", request.getPassword(), "password should be set");
        assertEquals("Test Description", request.getDescription(), "description should be set");
        assertEquals(UserGenderEntity.MALE, request.getUserGender(), "userGender should be set");
        assertEquals(UserRoleEntity.builder().build(), request.getUserRole(), "userRole should be set");
    }

    @Test
    public void testBuilder() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("testUser")
                .password("testPassword")
                .description("Test Description")
                .userGender(UserGenderEntity.MALE)
                .userRole(UserRoleEntity.builder().build())
                .build();

        assertNotNull(request, "Builder should create an instance");

        assertEquals("testUser", request.getUsername(), "username should be set");
        assertEquals("testPassword", request.getPassword(), "password should be set");
        assertEquals("Test Description", request.getDescription(), "description should be set");
        assertEquals(UserGenderEntity.MALE, request.getUserGender(), "userGender should be set");
        assertEquals(UserRoleEntity.builder().build(), request.getUserRole(), "userRole should be set");
    }
}