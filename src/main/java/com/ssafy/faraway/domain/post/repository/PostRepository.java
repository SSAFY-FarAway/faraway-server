package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
