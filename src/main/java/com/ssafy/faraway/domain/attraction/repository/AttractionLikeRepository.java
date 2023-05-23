package com.ssafy.faraway.domain.attraction.repository;

import com.ssafy.faraway.domain.attraction.entity.AttractionLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionLikeRepository extends JpaRepository<AttractionLike, Long> {
}
