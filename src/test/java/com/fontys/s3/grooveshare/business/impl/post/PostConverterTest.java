package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostConverterTest {

    @Test
    void convert_ShouldConvertPostEntityToPost() {
        PostEntity postEntity = mock(PostEntity.class);
        when(postEntity.getPostId()).thenReturn(1L);
        when(postEntity.getContent()).thenReturn("Test content");
        when(postEntity.getTrackId()).thenReturn("1");

        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserId()).thenReturn(3L);

        GenreEntity genreEntity = mock(GenreEntity.class);
        when(genreEntity.getId()).thenReturn(4L);

        when(postEntity.getUser()).thenReturn(userEntity);
        when(postEntity.getGenre()).thenReturn(genreEntity);

        Post convertedPost = PostConverter.convert(postEntity);

        assertEquals(postEntity.getPostId(), convertedPost.getPostId());
        assertEquals(postEntity.getContent(), convertedPost.getContent());
        assertEquals(postEntity.getTrackId(), convertedPost.getTrackId());
        assertEquals(userEntity.getUserId(), convertedPost.getUser().getUserId());
        assertEquals(genreEntity.getId(), convertedPost.getGenre().getId());
    }
}