package com.fontys.s3.grooveshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.s3.grooveshare.business.dtos.postdto.*;
import com.fontys.s3.grooveshare.business.post.*;
import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CreatePostUseCase createPostUseCase;
    @MockBean
    private GetAllPostsUseCase getAllPostsUseCase;
    @MockBean
    private GetPostUseCase getPostUseCase;
    @MockBean
    private GetPostsByGenreIdUseCase getPostsByGenreIdUseCase;


    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testCreatePost() throws Exception {
        CreatePostRequest request = CreatePostRequest.builder()
                .content("Test Content")
                .userId(1L)
                .genreId(1l)
                .build();

        CreatePostResponse response = CreatePostResponse.builder()
                .postId(1L)
                .content("Test Content")
                .genreId(1L)
                .build();

        when(createPostUseCase.createPost(any(CreatePostRequest.class))).thenReturn(response);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testGetAllPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder().postId(1L).content("Test Content").build());
        GetAllPostsResponse response = GetAllPostsResponse.builder().posts(posts).build();

        when(getAllPostsUseCase.getPosts(any(GetAllPostsRequest.class))).thenReturn(response);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts[0].postId").value(1L))
                .andExpect(jsonPath("$.posts[0].content").value("Test Content"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testGetPostsByGenreId() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder().postId(1L).content("Test Content").genre(Genre.builder().id(1L).genre("ROCK").build()).build());
        Long genreId = 1L;

        GetPostsByGenreIdRequest expectedRequest = GetPostsByGenreIdRequest.builder()
                .genreId(genreId)
                .build();

        GetPostsByGenreIdResponse mockResponse = GetPostsByGenreIdResponse.builder()
                .posts(posts)
                .build();

        when(getPostsByGenreIdUseCase.getPostsByGenreId(expectedRequest)).thenReturn(mockResponse);

        mockMvc.perform(get("/posts//byGenre/{genreId}", genreId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.posts.length()").value(1));

        verify(getPostsByGenreIdUseCase, times(1)).getPostsByGenreId(expectedRequest);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testGetPost() throws Exception {
        Post post = Post.builder().postId(1L).content("Test Content").build();
        Optional<Post> postOptional = Optional.of(post);

        when(getPostUseCase.getPost(1L)).thenReturn(postOptional);

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }
}