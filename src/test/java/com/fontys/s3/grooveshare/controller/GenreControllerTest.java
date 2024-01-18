package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.GetAllGenresUseCse;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;
import com.fontys.s3.grooveshare.config.TestConfig;
import com.fontys.s3.grooveshare.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
@WithMockUser(username = "testuser", roles = "USER")
@Import(TestConfig.class)
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllGenresUseCse getAllGenresUseCse;

    @Test
    void testGetAllGenres() throws Exception {
        GetAllGenresResponse response = GetAllGenresResponse.builder()
                .genres(Arrays.asList(
                        Genre.builder().genre("Genre1").build(),
                        Genre.builder().genre("Genre2").build(),
                        Genre.builder().genre("Genre3").build()
                ))
                .build();

        when(getAllGenresUseCse.getAllGenres(any(GetAllGenresRequest.class))).thenReturn(response);

        mockMvc.perform(get("/genres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genres[0].genre").value("Genre1"))
                .andExpect(jsonPath("$.genres[1].genre").value("Genre2"))
                .andExpect(jsonPath("$.genres[2].genre").value("Genre3"));
    }

    @Test
    void testGetAllGenresFailure() throws Exception {
        // Arrange
        GetAllGenresRequest request = GetAllGenresRequest.builder().build();
        when(getAllGenresUseCse.getAllGenres(request)).thenThrow(new RuntimeException("Test exception"));

        // Act and Assert
        mockMvc.perform(get("/genres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(getAllGenresUseCse, times(1)).getAllGenres(request);
    }
}