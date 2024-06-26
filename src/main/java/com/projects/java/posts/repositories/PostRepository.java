package com.projects.java.posts.repositories;

import com.projects.java.posts.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllByTitleContaining(String title);
    List<Post> findAllByContentContaining(String content);

    List<Post> findAllByTitleContainingOrContentContaining(String title, String content);

}
