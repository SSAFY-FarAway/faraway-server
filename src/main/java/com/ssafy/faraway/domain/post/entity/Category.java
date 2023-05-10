package com.ssafy.faraway.domain.post.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 30)
    private String categoryName;

    @Builder
    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
