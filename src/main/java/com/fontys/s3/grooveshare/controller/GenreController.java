package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.dtos.GetAllGenresRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllGenresResponse;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.business.GetAllGenresUseCse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:*", allowCredentials = "true")
public class GenreController {
    private final GetAllGenresUseCse getAllGenresUseCse;
    @GetMapping
    public ResponseEntity<GetAllGenresResponse> getAllGenres() {
        GetAllGenresRequest request = GetAllGenresRequest.builder().build();
        GetAllGenresResponse response = getAllGenresUseCse.getAllGenres(request);
        return ResponseEntity.ok(response);
    }
}
