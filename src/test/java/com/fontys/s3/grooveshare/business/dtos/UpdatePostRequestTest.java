package com.fontys.s3.grooveshare.business.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePostRequestTest {
    @Test
    public void testNoArgsConstructor() {
        UpdatePostRequest request = new UpdatePostRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
        assertNull(request.getPostId(), "PostId should be null");
        assertNull(request.getContent(), "Content should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        UpdatePostRequest request = new UpdatePostRequest(1L, "testContent");
        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals(1L, request.getPostId(), "PostId should be set");
        assertEquals("testContent", request.getContent(), "Content should be set");
    }

    @Test
    public void testBuilder() {
        UpdatePostRequest request = UpdatePostRequest.builder()
                .postId(1L)
                .content("testContent")
                .build();

        assertNotNull(request, "Builder should create an instance");
        assertEquals(1L, request.getPostId(), "PostId should be set");
        assertEquals("testContent", request.getContent(), "Content should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        UpdatePostRequest request1 = UpdatePostRequest.builder().postId(1L).content("testContent").build();
        UpdatePostRequest request2 = UpdatePostRequest.builder().postId(1L).content("testContent").build();
        UpdatePostRequest request3 = UpdatePostRequest.builder().postId(2L).content("differentContent").build();

        assertEquals(request1, request2, "Objects with the same attributes should be equal");
        assertNotEquals(request1, request3, "Objects with different attributes should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes of different objects should not be the same");
    }

}