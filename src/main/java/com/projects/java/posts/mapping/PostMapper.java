package com.projects.java.posts.mapping;

import com.projects.java.posts.dto.PostDTO;
import com.projects.java.posts.models.Post;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@RequiredArgsConstructor
@Component
public class PostMapper {

    public PostDTO intoPostDTO(Post post){
        return PostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .userEmail(post.getUser().getEmail())
                .build();
    }

    public List<PostDTO> listIntoPostDTO(List<Post> posts){
        return posts.stream()
                .map(this::intoPostDTO)
                .collect(Collectors.toList());
    }

}
