package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
