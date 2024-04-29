package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.dto.PostDTO;
import com.projects.java.posts.models.Post;
import org.springframework.data.domain.Page;

public interface PostService {
    void createPost(NewPostRequest request, String userEmail);
    boolean updatePost(NewPostRequest updatedContent, Long postId, String userEmail);
    boolean deletePost(Long postId, String userEmail);
    Post readPost(Long postId);
    Page<Post> getPage(int page, int size);
    Page<Post> getSortedPage(int page, int size, String direction, String sortBy);
    Page<Post> getPostByTitle(int page, int size, String title);
    Page<Post> getPostByContent(int page, int size, String content);
    Page<Post> getPostByTitleOrContent(int page, int size, String request);
}
