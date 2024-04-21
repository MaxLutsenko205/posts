package com.projects.java.posts.controllers;

import com.projects.java.posts.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPostRequest {
    private String title;
    private String content;
}
