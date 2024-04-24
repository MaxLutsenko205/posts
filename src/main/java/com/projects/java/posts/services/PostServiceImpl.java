package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.models.Role;
import com.projects.java.posts.models.User;
import com.projects.java.posts.repositories.PostRepository;
import com.projects.java.posts.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public void createPost(NewPostRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new EntityNotFoundException("User not found"));
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    @Override
    public boolean updatePost(NewPostRequest updatedContent, Long postId, String userEmail) {
        if (hasAccess(userEmail, postId)){
            Post post = postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Post not found"));
            post.setTitle(updatedContent.getTitle());
            post.setContent(updatedContent.getContent());
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePost(Long postId, String userEmail) {
        if (hasAccess(userEmail, postId)){
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    @Override
    public Post readPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found"));
    }

    public boolean hasAccess(String userEmail, Long postId){
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if (user.getRole()==Role.ADMIN){
            return true;
        }
        return user == post.getUser();
    }
}
