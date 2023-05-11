package com.ssafy.faraway.domain.hotplace.repository;

import com.ssafy.faraway.domain.hotplace.entity.HotPlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotPlaceCommentRepository extends JpaRepository<HotPlaceComment, Long> {
}
