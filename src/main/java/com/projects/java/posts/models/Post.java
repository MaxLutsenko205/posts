package com.projects.java.posts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    public void setTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
