package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
