package com.ssafy.faraway.domain.hotplace.repository;

import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotPlaceRepository extends JpaRepository<HotPlace, Long> {
}
