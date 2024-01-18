package com.fontys.s3.grooveshare.business.postInterface;

import com.fontys.s3.grooveshare.business.dtos.postDtos.GetPostsByGenreIdRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetPostsByGenreIdResponse;

public interface GetPostsByGenreIdUseCase {
    GetPostsByGenreIdResponse getPostsByGenreId(GetPostsByGenreIdRequest request);
}
