package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
