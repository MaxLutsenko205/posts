package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.models.Post;

public interface PostService {
    void createPost(NewPostRequest request, String userEmail);
    boolean updatePost(NewPostRequest updatedContent, Long postId, String userEmail);
    boolean deletePost(Long postId, String userEmail);
    Post readPost(Long postId);
}
