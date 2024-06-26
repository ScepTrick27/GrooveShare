package com.fontys.s3.grooveshare.business.impl.post;

import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsRequest;
import com.fontys.s3.grooveshare.business.dtos.postdto.GetAllPostsResponse;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.entity.GenreEntity;
import com.fontys.s3.grooveshare.persistance.entity.PostEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        UserEntity user2 = UserEntity.builder().userId(2L).username("user2").build();

        GenreEntity mockGenre = GenreEntity.builder().id(1L).genre("Rock").build();

        postEntities.add(PostEntity.builder().postId(1L).content("Post 1 content").user(user1).genre(mockGenre).build());
        postEntities.add(PostEntity.builder().postId(2L).content("Post 2 content").user(user2).genre(mockGenre).build());

        PageImpl<PostEntity> page = new PageImpl<>(postEntities);

        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("postId").descending());
        when(postRepository.getAllPosts(pageRequest)).thenReturn(page);

        GetAllPostsRequest request = GetAllPostsRequest.builder().page(1).size(10).build();
        GetAllPostsResponse response = getPostsUseCase.getPosts(request);

        verify(postRepository, times(1)).getAllPosts(pageRequest);

        assertEquals(2, response.getPosts().size());
        assertEquals("Post 2 content", response.getPosts().get(1).getContent());
        assertEquals("Post 1 content", response.getPosts().get(0).getContent());
    }

    @Test
    void getPosts_EmptyList_ThrowsIllegalArgumentException() {
        GetAllPostsRequest request = GetAllPostsRequest.builder().page(0).size(0).build();

        assertThrows(IllegalArgumentException.class, () -> getPostsUseCase.getPosts(request));
    }

    @Test
    void getPosts_NegativePageNumberAndSize_ThrowsIllegalArgumentException() {
        GetAllPostsRequest request = GetAllPostsRequest.builder().page(-1).size(-1).build();
        assertThrows(IllegalArgumentException.class, () -> getPostsUseCase.getPosts(request));
    }
}