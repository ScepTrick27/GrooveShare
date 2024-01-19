package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.FindPostsByFollowersResponse;
import com.fontys.s3.grooveshare.business.impl.FindPostsByFollowersImpl;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPostsByFollowersImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private FindPostsByFollowersImpl findPostsByFollowers;

    @Test
    void testFindPostsByLoggedInUserAndFollowers() {
        Long userId = 1L;
        List<PostEntity> mockPostEntities = Collections.singletonList(new PostEntity());
        when(postRepository.findPostsByLoggedInUserAndFollowers(userId)).thenReturn(mockPostEntities);

        FindPostsByFollowersResponse response = findPostsByFollowers.findPostsByLoggedInUserAndFollowers(userId);

        assertEquals(mockPostEntities, response.getPosts());
    }

    @Test
    void testFindPostsByLoggedInUserAndNoFollowers() {
        Long userId = 1L;
        when(postRepository.findPostsByLoggedInUserAndFollowers(userId)).thenReturn(Collections.emptyList());

        FindPostsByFollowersResponse response = findPostsByFollowers.findPostsByLoggedInUserAndFollowers(userId);

        assertNotNull(response);
        assertNotNull(response.getPosts());
        assertTrue(response.getPosts().isEmpty());
    }
}