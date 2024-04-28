package com.projects.java.posts.services;

import com.projects.java.posts.controllers.NewPostRequest;
import com.projects.java.posts.dto.PostDTO;
import com.projects.java.posts.mapping.PostMapper;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.models.Role;
import com.projects.java.posts.models.User;
import com.projects.java.posts.repositories.PostRepository;
import com.projects.java.posts.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<Post> getPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return postRepository.findAll(pageRequest);
    }

    @Override
    public Page<Post> getSortedPage(int page, int size, String direction, String sortBy){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return postRepository.findAll(pageRequest);
    }

//    @Override
//    public Page<Post> getPostByTitle(String title){
//        List<Post> posts = postRepository.findAllByTitleContaining(title);
//
//        return ;
//    }
    public boolean hasAccess(String userEmail, Long postId){
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if (user.getRole()==Role.ADMIN){
            return true;
        }
        return user == post.getUser();
    }
}
