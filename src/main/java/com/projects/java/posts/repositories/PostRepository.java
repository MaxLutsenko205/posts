package com.projects.java.posts.repositories;

import com.projects.java.posts.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {
    Optional<Post> findByUserEmail(String userEmail);

}
