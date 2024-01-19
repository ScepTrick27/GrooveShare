package com.fontys.s3.grooveshare.business.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetPostsByGenreIdResponse;

public interface GetPostsByGenreIdUseCase {
    GetPostsByGenreIdResponse getPostsByGenreId(GetPostsByGenreIdRequest request);
}
