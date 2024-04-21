package com.projects.java.posts.controllers;

import com.projects.java.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final PostService postService;
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public void newPost(@RequestBody NewPostRequest request){
        postService.createPost(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get(){return "admin:GET";}
}
