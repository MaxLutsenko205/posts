package com.projects.java.posts.dto;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private String userEmail;
}
