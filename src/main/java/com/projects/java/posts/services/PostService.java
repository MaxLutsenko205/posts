package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface PostService {
    void createPost(NewPostRequest request);
    void updatePost(String updatedContent, String userEmail);
    void deletePost();
    Post readPost(String userEmail);
}
