package com.fontys.s3.grooveshare.business.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePostResponseTest {
    @Test
    public void testNoArgsConstructor() {
        CreatePostResponse response = new CreatePostResponse();
        assertNotNull(response, "NoArgsConstructor should create an instance");
    }

    @Test
    public void testAllArgsConstructor() {
        CreatePostResponse response = new CreatePostResponse(1L, "Test Content");
        assertNotNull(response, "AllArgsConstructor should create an instance");

        assertEquals(1L, response.getPostId(), "postId should be set");
        assertEquals("Test Content", response.getContent(), "content should be set");
    }

    @Test
    public void testBuilder() {
        CreatePostResponse response = CreatePostResponse.builder()
                .postId(1L)
                .content("Test Content")
                .build();

        assertNotNull(response, "Builder should create an instance");

        assertEquals(1L, response.getPostId(), "postId should be set");
        assertEquals("Test Content", response.getContent(), "content should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        CreatePostResponse response1 = CreatePostResponse.builder().postId(1L).content("Test").build();
        CreatePostResponse response2 = CreatePostResponse.builder().postId(1L).content("Test").build();
        CreatePostResponse response3 = CreatePostResponse.builder().postId(2L).content("Different").build();

        assertEquals(response1, response2, "Objects with the same attributes should be equal");
        assertNotEquals(response1, response3, "Objects with different attributes should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes of different objects should not be the same");
    }

    @Test
    public void testToString() {
        CreatePostResponse response = CreatePostResponse.builder().postId(1L).content("Test").build();
        assertNotNull(response.toString(), "ToString should not be null");
    }

}