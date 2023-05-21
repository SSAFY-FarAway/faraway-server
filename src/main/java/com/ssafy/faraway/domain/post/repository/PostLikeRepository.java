package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
