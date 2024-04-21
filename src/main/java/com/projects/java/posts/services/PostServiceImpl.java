package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.repositories.PostRepository;
import com.projects.java.posts.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public void createPost(NewPostRequest request) {
        var post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        postRepository.save(post);
    }

    @Override
    public void updatePost(String userEmail, String updatedContent) {
        Optional<Post> post = postRepository.findByUserEmail(userEmail);
    }

    @Override
    public void deletePost() {

    }

    @Override
    public Post readPost(String userEmail) {
        return null;
    }
}
