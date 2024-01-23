package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.GetAllGenresUseCse;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;
import com.fontys.s3.grooveshare.domain.Genre;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllGenresUseCse getAllGenresUseCse;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
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
    @WithMockUser(username = "testuser", roles = "USER")
    void testGetAllGenresFailure() throws Exception {
        GetAllGenresRequest request = GetAllGenresRequest.builder().build();
        when(getAllGenresUseCse.getAllGenres(request)).thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/genres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(getAllGenresUseCse, times(1)).getAllGenres(request);
    }
}