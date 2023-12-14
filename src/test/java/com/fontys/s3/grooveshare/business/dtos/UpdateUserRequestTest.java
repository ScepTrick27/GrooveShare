package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.userDtos.UpdateUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserRequestTest {
    @Test
    public void testNoArgsConstructor() {
        UpdateUserRequest request = new UpdateUserRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
        assertNull(request.getUserId(), "UserId should be null");
        assertNull(request.getUsername(), "Username should be null");
        assertNull(request.getDescription(), "Description should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        UpdateUserRequest request = new UpdateUserRequest(1L, "testUser", "testDescription");
        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals(1L, request.getUserId(), "UserId should be set");
        assertEquals("testUser", request.getUsername(), "Username should be set");
        assertEquals("testDescription", request.getDescription(), "Description should be set");
    }

    @Test
    public void testBuilder() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .userId(1L)
                .username("testUser")
                .description("testDescription")
                .build();

        assertNotNull(request, "Builder should create an instance");
        assertEquals(1L, request.getUserId(), "UserId should be set");
        assertEquals("testUser", request.getUsername(), "Username should be set");
        assertEquals("testDescription", request.getDescription(), "Description should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        UpdateUserRequest request1 = UpdateUserRequest.builder().userId(1L).username("testUser").description("testDescription").build();
        UpdateUserRequest request2 = UpdateUserRequest.builder().userId(1L).username("testUser").description("testDescription").build();
        UpdateUserRequest request3 = UpdateUserRequest.builder().userId(2L).username("differentUser").description("differentDescription").build();

        assertEquals(request1, request2, "Objects with the same attributes should be equal");
        assertNotEquals(request1, request3, "Objects with different attributes should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes of different objects should not be the same");
    }
}