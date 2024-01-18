package com.fontys.s3.grooveshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Long id;

    private String genre;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Genre other = (Genre) obj;
        return Objects.equals(this.genre, other.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}
