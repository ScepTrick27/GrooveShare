package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllUsersResponseTest {
    @Test
    public void testNoArgsConstructor() {
        GetAllUsersResponse response = new GetAllUsersResponse();
        assertNotNull(response, "NoArgsConstructor should create an instance");
        assertNull(response.getUsers(), "List should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        List<User> users = Arrays.asList(new User(), new User());
        GetAllUsersResponse response = new GetAllUsersResponse(users);
        assertNotNull(response, "AllArgsConstructor should create an instance");

        assertEquals(users, response.getUsers(), "users should be set");
    }

    @Test
    public void testBuilder() {
        List<User> users = Arrays.asList(new User(), new User());
        GetAllUsersResponse response = GetAllUsersResponse.builder()
                .users(users)
                .build();

        assertNotNull(response, "Builder should create an instance");
        assertEquals(users, response.getUsers(), "users should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        List<User> users1 = Arrays.asList(new User(), new User());
        List<User> users2 = Arrays.asList(new User(), new User());
        List<User> users3 = Arrays.asList(new User());

        GetAllUsersResponse response1 = GetAllUsersResponse.builder().users(users1).build();
        GetAllUsersResponse response2 = GetAllUsersResponse.builder().users(users2).build();
        GetAllUsersResponse response3 = GetAllUsersResponse.builder().users(users3).build();

        assertEquals(response1, response2, "Objects with the same attributes should be equal");
        assertNotEquals(response1, response3, "Objects with different attributes should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes of different objects should not be the same");
    }
}