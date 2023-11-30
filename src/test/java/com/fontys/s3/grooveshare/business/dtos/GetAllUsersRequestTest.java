package com.fontys.s3.grooveshare.business.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetAllUsersRequestTest {
    @Test
    public void testNoArgsConstructor() {
        GetAllUsersRequest request = new GetAllUsersRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
        assertEquals(0L, request.getUserId(), "userId should be initialized to 0");
    }

    @Test
    public void testAllArgsConstructor() {
        GetAllUsersRequest request = new GetAllUsersRequest(1L);
        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals(1L, request.getUserId(), "userId should be set");
    }

    @Test
    public void testBuilder() {
        GetAllUsersRequest request = GetAllUsersRequest.builder()
                .userId(1L)
                .build();

        assertNotNull(request, "Builder should create an instance");
        assertEquals(1L, request.getUserId(), "userId should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        GetAllUsersRequest request1 = GetAllUsersRequest.builder().userId(1L).build();
        GetAllUsersRequest request2 = GetAllUsersRequest.builder().userId(1L).build();
        GetAllUsersRequest request3 = GetAllUsersRequest.builder().userId(2L).build();

        assertEquals(request1, request2, "Objects with the same attributes should be equal");
        assertNotEquals(request1, request3, "Objects with different attributes should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes of different objects should not be the same");
    }
}