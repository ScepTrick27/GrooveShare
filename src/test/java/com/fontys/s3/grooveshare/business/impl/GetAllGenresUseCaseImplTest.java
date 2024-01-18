package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;
import com.fontys.s3.grooveshare.persistance.GenreRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllGenresUseCaseImplTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GetAllGenresUseCaseImpl getAllGenresUseCase;

    @Test
    void getAllGenres_ReturnsEmptyList_WhenNoGenresInRepository() {
        // Arrange
        when(genreRepository.findAll()).thenReturn(List.of());

        // Act
        GetAllGenresResponse response = getAllGenresUseCase.getAllGenres(new GetAllGenresRequest());

        // Assert
        assertEquals(0, response.getGenres().size());
        verify(genreRepository, times(1)).findAll();
    }

    @Test
    void getAllGenres_ReturnsGenres_WhenGenresInRepository() {
        // Arrange
        List<GenreEntity> genreEntities = Arrays.asList(
                new GenreEntity(1L, "Rock"),
                new GenreEntity(2L, "Pop")
        );
        when(genreRepository.findAll()).thenReturn(genreEntities);

        // Act
        GetAllGenresResponse response = getAllGenresUseCase.getAllGenres(new GetAllGenresRequest());

        // Assert
        assertEquals(2, response.getGenres().size());
        assertEquals("Rock", response.getGenres().get(0).getGenre());
        assertEquals("Pop", response.getGenres().get(1).getGenre());
        verify(genreRepository, times(1)).findAll();
    }
}