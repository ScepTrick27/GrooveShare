package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.userDtos.LogInUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInUserRequestTest {
    @Test
    public void testNoArgsConstructor() {
        LogInUserRequest request = new LogInUserRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
        assertNull(request.getUsername(), "Username should be null");
        assertNull(request.getPassword(), "Password should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        LogInUserRequest request = new LogInUserRequest("testUser", "testPassword");
        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals("testUser", request.getUsername(), "Username should be set");
        assertEquals("testPassword", request.getPassword(), "Password should be set");
    }

    @Test
    public void testBuilder() {
        LogInUserRequest request = LogInUserRequest.builder()
                .username("testUser")
                .password("testPassword")
                .build();

        assertNotNull(request, "Builder should create an instance");
        assertEquals("testUser", request.getUsername(), "Username should be set");
        assertEquals("testPassword", request.getPassword(), "Password should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        LogInUserRequest request1 = LogInUserRequest.builder().username("testUser").password("testPassword").build();
        LogInUserRequest request2 = LogInUserRequest.builder().username("testUser").password("testPassword").build();
        LogInUserRequest request3 = LogInUserRequest.builder().username("differentUser").password("testPassword").build();

        assertEquals(request1, request2, "Objects with the same attributes should be equal");
        assertNotEquals(request1, request3, "Objects with different attributes should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes of different objects should not be the same");
    }
}