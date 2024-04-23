package com.projects.java.posts.dto;

import com.projects.java.posts.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private String userEmail;
}
