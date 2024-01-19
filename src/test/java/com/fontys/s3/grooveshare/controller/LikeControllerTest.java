package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.IsLikedUseCase;
import com.fontys.s3.grooveshare.business.LikePostUseCase;
import com.fontys.s3.grooveshare.business.UnlikePostUseCase;
import com.fontys.s3.grooveshare.business.dtos.IsLikedResponse;
import com.fontys.s3.grooveshare.business.dtos.LikePostRequest;
import com.fontys.s3.grooveshare.business.dtos.LikePostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class LikeControllerTest {
    @MockBean
    private LikePostUseCase likePostUseCase;

    @MockBean
    private UnlikePostUseCase unlikePostUseCase;

    @MockBean
    private IsLikedUseCase isLikedUseCase;

    @InjectMocks
    private LikeController likeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testLikePostSuccess() throws Exception {
        LikePostRequest request = LikePostRequest.builder().userId(1L).postId(2L).build();
        LikePostResponse mockResponse = LikePostResponse.builder().success(true).build();
        when(likePostUseCase.likePost(request)).thenReturn(mockResponse);

        mockMvc.perform(post("/likes/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1, \"postId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true));

        verify(likePostUseCase, times(1)).likePost(request);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testUnlikePostSuccess() throws Exception {
        LikePostRequest request = LikePostRequest.builder().userId(1L).postId(2L).build();
        LikePostResponse mockResponse = LikePostResponse.builder().success(true).build();
        when(unlikePostUseCase.unlikePost(request)).thenReturn(mockResponse);

        mockMvc.perform(post("/likes/unlike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1, \"postId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true));

        verify(unlikePostUseCase, times(1)).unlikePost(request);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testHasUserLikedPostSuccess() throws Exception {
        when(isLikedUseCase.isLiked(any())).thenReturn(IsLikedResponse.builder().isLiked(true).build());

        mockMvc.perform(get("/likes/hasLiked")
                        .param("userId", "1")
                        .param("postId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.liked").value(true));

        verify(isLikedUseCase, times(1)).isLiked(any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testLikePostInvalidInput() throws Exception {
        mockMvc.perform(post("/likes/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        verify(likePostUseCase, never()).likePost(any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testLikePostUseCaseFailure() throws Exception {
        LikePostRequest request = LikePostRequest.builder().userId(1L).postId(2L).build();
        when(likePostUseCase.likePost(request)).thenThrow(new RuntimeException("Internal server error"));

        mockMvc.perform(post("/likes/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1, \"postId\": 2}"))
                .andExpect(status().isInternalServerError());

        verify(likePostUseCase, times(1)).likePost(request);
    }
}