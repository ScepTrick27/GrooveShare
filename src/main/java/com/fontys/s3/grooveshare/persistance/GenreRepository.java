package com.fontys.s3.grooveshare.persistance;

import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
