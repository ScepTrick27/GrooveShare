package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;

public interface GetAllGenresUseCse {
    GetAllGenresResponse getAllGenres (GetAllGenresRequest request);
}
