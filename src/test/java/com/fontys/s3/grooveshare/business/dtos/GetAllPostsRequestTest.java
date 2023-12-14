package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetAllPostsRequestTest {
    @Test
    public void testNoArgsConstructor() {
        GetAllPostsRequest request = new GetAllPostsRequest();
        assertNotNull(request, "NoArgsConstructor should create an instance");
    }

    @Test
    public void testAllArgsConstructor() {
        GetAllPostsRequest request = new GetAllPostsRequest(1L);
        assertNotNull(request, "AllArgsConstructor should create an instance");

        assertEquals(1L, request.getPostId(), "postId should be set");
    }

    @Test
    public void testBuilder() {
        GetAllPostsRequest request = GetAllPostsRequest.builder()
                .postId(1L)
                .build();

        assertNotNull(request, "Builder should create an instance");
        assertEquals(1L, request.getPostId(), "postId should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        GetAllPostsRequest request1 = GetAllPostsRequest.builder().postId(1L).build();
        GetAllPostsRequest request2 = GetAllPostsRequest.builder().postId(1L).build();
        GetAllPostsRequest request3 = GetAllPostsRequest.builder().postId(2L).build();

        assertEquals(request1, request2, "Objects with the same attributes should be equal");
        assertNotEquals(request1, request3, "Objects with different attributes should not be equal");

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Hash codes of different objects should not be the same");
    }
}