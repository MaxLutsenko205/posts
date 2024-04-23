package com.projects.java.posts.controllers;

import com.projects.java.posts.models.Post;
import com.projects.java.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class AdminController {

    private final PostService postService;
    @PostMapping("/create")
    public void newPost(@RequestBody NewPostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        postService.createPost(request, userEmail);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.readPost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updateUserPost(@PathVariable Long postId, @RequestBody NewPostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(postService.updatePost(request, postId, userEmail));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(postService.deletePost(postId, userEmail));
    }


}
