package com.fontys.s3.grooveshare.business.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserResponseTest {
    @Test
    public void testNoArgsConstructor() {
        CreateUserResponse response = new CreateUserResponse();
        assertNotNull(response, "NoArgsConstructor should create an instance");
    }

    @Test
    public void testAllArgsConstructor() {
        CreateUserResponse response = new CreateUserResponse(1L);
        assertNotNull(response, "AllArgsConstructor should create an instance");

        assertEquals(1L, response.getUserId(), "userId should be set");
    }

    @Test
    public void testBuilder() {
        CreateUserResponse response = CreateUserResponse.builder()
                .userId(1L)
                .build();

        assertNotNull(response, "Builder should create an instance");
        assertEquals(1L, response.getUserId(), "userId should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        CreateUserResponse response1 = CreateUserResponse.builder().userId(1L).build();
        CreateUserResponse response2 = CreateUserResponse.builder().userId(1L).build();
        CreateUserResponse response3 = CreateUserResponse.builder().userId(2L).build();

        assertEquals(response1, response2, "Objects with the same attributes should be equal");
        assertNotEquals(response1, response3, "Objects with different attributes should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes of different objects should not be the same");
    }

}