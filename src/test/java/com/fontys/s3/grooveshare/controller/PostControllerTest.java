package com.fontys.s3.grooveshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.s3.grooveshare.business.FindPostsByFollowersUseCase;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetUserPostCountResponse;
import com.fontys.s3.grooveshare.business.postInterface.*;
import com.fontys.s3.grooveshare.config.TestConfig;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@Import(TestConfig.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private CreatePostUseCase createPostUseCase;

    @MockBean
    private UpdatePostUseCase updatePostUseCase;

    @MockBean
    private DeletePostUseCase deletePostUseCase;

    @MockBean
    private GetAllPostsUseCase getAllPostsUseCase;

    @MockBean
    private GetPostUseCase getPostUseCase;

    @MockBean
    private UserPostCountUseCase userPostCountUseCase;

    @MockBean
    private FindPostsByFollowersUseCase findPostsByFollowersUseCase;

//    @Test
//    @WithMockUser(username = "testuser", roles = "USER")
//    public void testCreatePost() throws Exception {
//        CreatePostRequest request = CreatePostRequest.builder()
//                .content("Test Content")
//                .userId(1L)
//                .build();
//
//        CreatePostResponse response = CreatePostResponse.builder()
//                .postId(1L)
//                .content("Test Content")
//                .build();
//
//        when(createPostUseCase.createPost(any(CreatePostRequest.class))).thenReturn(response);
//        mockMvc.perform(post("/posts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.postId").value(1L))
//                .andExpect(jsonPath("$.content").value("Test Content"));
//    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testGetAllPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder().postId(1L).content("Test Content").build());
        GetAllPostsResponse response = GetAllPostsResponse.builder().posts(posts).build();

        when(getAllPostsUseCase.getPosts(any(GetAllPostsRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts[0].postId").value(1L))
                .andExpect(jsonPath("$.posts[0].content").value("Test Content"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testGetPost() throws Exception {
        Post post = Post.builder().postId(1L).content("Test Content").build();
        Optional<Post> postOptional = Optional.of(post);

        when(getPostUseCase.getPost(1L)).thenReturn(postOptional);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }
}