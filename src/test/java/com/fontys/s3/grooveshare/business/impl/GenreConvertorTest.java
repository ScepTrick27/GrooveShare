package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreConvertorTest {
    @Test
    void convert_ShouldConvertGenreEntityToGenre() {
        GenreEntity genreEntity = mock(GenreEntity.class);
        when(genreEntity.getId()).thenReturn(1L);
        when(genreEntity.getGenre()).thenReturn("Rock");

        Genre convertedGenre = GenreConvertor.convert(genreEntity);

        assertEquals(genreEntity.getId(), convertedGenre.getId());
        assertEquals(genreEntity.getGenre(), convertedGenre.getGenre());
    }
}