package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.userDtos.LogInUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInUserResponseTest {
    @Test
    public void testNoArgsConstructor() {
        LogInUserResponse response = new LogInUserResponse();
        assertNotNull(response, "NoArgsConstructor should create an instance");
        assertNull(response.getAccessToken(), "AccessToken should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        LogInUserResponse response = new LogInUserResponse("testToken");
        assertNotNull(response, "AllArgsConstructor should create an instance");

        assertEquals("testToken", response.getAccessToken(), "AccessToken should be set");
    }

    @Test
    public void testBuilder() {
        LogInUserResponse response = LogInUserResponse.builder()
                .accessToken("testToken")
                .build();

        assertNotNull(response, "Builder should create an instance");
        assertEquals("testToken", response.getAccessToken(), "AccessToken should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        LogInUserResponse response1 = LogInUserResponse.builder().accessToken("testToken").build();
        LogInUserResponse response2 = LogInUserResponse.builder().accessToken("testToken").build();
        LogInUserResponse response3 = LogInUserResponse.builder().accessToken("differentToken").build();

        assertEquals(response1, response2, "Objects with the same attributes should be equal");
        assertNotEquals(response1, response3, "Objects with different attributes should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes of different objects should not be the same");
    }
}