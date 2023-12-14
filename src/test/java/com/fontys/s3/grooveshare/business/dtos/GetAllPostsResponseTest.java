package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.domain.Post;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllPostsResponseTest {
    @Test
    public void testNoArgsConstructor() {
        GetAllPostsResponse response = new GetAllPostsResponse();
        assertNotNull(response, "NoArgsConstructor should create an instance");
        assertNull(response.getPosts(), "List should be null");
    }

    @Test
    public void testAllArgsConstructor() {
        List<Post> posts = Arrays.asList(new Post(), new Post());
        GetAllPostsResponse response = new GetAllPostsResponse(posts);
        assertNotNull(response, "AllArgsConstructor should create an instance");

        assertEquals(posts, response.getPosts(), "posts should be set");
    }

    @Test
    public void testBuilder() {
        List<Post> posts = Arrays.asList(new Post(), new Post());
        GetAllPostsResponse response = GetAllPostsResponse.builder()
                .posts(posts)
                .build();

        assertNotNull(response, "Builder should create an instance");
        assertEquals(posts, response.getPosts(), "posts should be set");
    }

    @Test
    public void testEqualsAndHashCode() {
        List<Post> posts1 = Arrays.asList(new Post(), new Post());
        List<Post> posts2 = Arrays.asList(new Post(), new Post());
        List<Post> posts3 = List.of(new Post());

        GetAllPostsResponse response1 = GetAllPostsResponse.builder().posts(posts1).build();
        GetAllPostsResponse response2 = GetAllPostsResponse.builder().posts(posts2).build();
        GetAllPostsResponse response3 = GetAllPostsResponse.builder().posts(posts3).build();

        assertEquals(response1, response2, "Objects with the same attributes should be equal");
        assertNotEquals(response1, response3, "Objects with different attributes should not be equal");

        assertEquals(response1.hashCode(), response2.hashCode(), "Hash codes of equal objects should be the same");
        assertNotEquals(response1.hashCode(), response3.hashCode(), "Hash codes of different objects should not be the same");
    }
}