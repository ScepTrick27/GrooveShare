package com.fontys.s3.grooveshare.business.impl.postImpl;

import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.GetAllPostsResponse;
import com.fontys.s3.grooveshare.business.impl.postImpl.GetPostsUseCaseImpl;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPostsUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostsUseCaseImpl getPostsUseCase;

    @Test
    void getPosts_ReturnsCorrectResponse() {
        List<PostEntity> postEntities = new ArrayList<>();

        UserEntity user1 = UserEntity.builder().userId(1L).username("user1").build();
        UserEntity user2 =UserEntity.builder().userId(2L).username("user2").build();

        postEntities.add(PostEntity.builder().postId(1L).content("Post 1 content").user(user1).build());
        postEntities.add(PostEntity.builder().postId(2L).content("Post 2 content").user(user2).build());

        when(postRepository.findAll()).thenReturn(postEntities);

        GetAllPostsRequest request = new GetAllPostsRequest();

        GetAllPostsResponse response = getPostsUseCase.getPosts(request);

        verify(postRepository, times(1)).findAll();

        assertEquals(2, response.getPosts().size());
        assertEquals("Post 1 content", response.getPosts().get(0).getContent());
        assertEquals("Post 2 content", response.getPosts().get(1).getContent());
    }
    @Test
    void getPosts_EmptyList_ReturnsEmptyResponse() {
        when(postRepository.findAll()).thenReturn(List.of());

        GetAllPostsResponse response = getPostsUseCase.getPosts(new GetAllPostsRequest());

        assertEquals(List.of(), response.getPosts());
    }

}