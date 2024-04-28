package com.projects.java.posts.controllers;

import com.projects.java.posts.dto.PostDTO;
import com.projects.java.posts.mapping.PostMapper;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.repositories.PostRepository;
import com.projects.java.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;
    private final PostMapper postMapper;

//    create post
    @PostMapping("/create")
    public void newPost(@RequestBody NewPostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        postService.createPost(request, userEmail);
    }

//    get post by post id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId){
        Post post = postService.readPost(postId);
        return ResponseEntity.ok(postMapper.intoPostDTO(post));
    }

//    find post by post id and update title and content
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody NewPostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(postService.updatePost(request, postId, userEmail));
    }

//    delete post by post id
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(postService.deletePost(postId, userEmail));
    }

//    get posts page
    @GetMapping
    public Page<Post> getPostsPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size){
        return postService.getPage(page, size);
    }

//    get sorted posts page
    @GetMapping("/sort")
    public Page<Post> getSortedPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "ASC") String direction,
                                    @RequestParam(defaultValue = "postId") String sortBy){
        return postService.getSortedPage(page, size, direction, sortBy);
    }

//    searching
//    @GetMapping("/title")
//    public Page<Post> getPostByTitle(@RequestParam String title){
//        return postService.getPostByTitle(title);
//    }
}
