package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;

public class GenreConvertor {
    private GenreConvertor() {
    }
    public static Genre convert(GenreEntity genre){
        return Genre.builder()
                .id(genre.getId())
                .genre(genre.getGenre())
                .build();
    }
}
