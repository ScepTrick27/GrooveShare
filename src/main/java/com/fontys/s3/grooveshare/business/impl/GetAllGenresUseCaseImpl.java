package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.GetAllGenresUseCse;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;
import com.fontys.s3.grooveshare.domain.Genre;
import com.fontys.s3.grooveshare.persistance.GenreRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllGenresUseCaseImpl implements GetAllGenresUseCse {
    private final GenreRepository genreRepository;
    @Override
    public GetAllGenresResponse getAllGenres(GetAllGenresRequest request) {
        List<GenreEntity> results = genreRepository.findAll();

        final GetAllGenresResponse response = new GetAllGenresResponse();
        List<Genre> genres = results
                .stream()
                .map(genreEntity -> GenreConvertor.convert(genreEntity))
                .toList();
        response.setGenres(genres);

        return response;
    }
}
