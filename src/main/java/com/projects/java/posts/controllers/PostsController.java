package com.projects.java.posts.controllers;

import com.projects.java.posts.dto.PostDTO;
import com.projects.java.posts.mapping.PostMapper;
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
public class PostsController {

    private final PostService postService;
    private final PostMapper postMapper;
    @PostMapping("/create")
    public void newPost(@RequestBody NewPostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        postService.createPost(request, userEmail);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId){
        Post post = postService.readPost(postId);
        return ResponseEntity.ok(postMapper.intoPostDTO(post));
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
