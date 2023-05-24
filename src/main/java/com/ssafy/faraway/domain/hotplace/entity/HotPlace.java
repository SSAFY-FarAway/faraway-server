package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotPlace extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_place_id")
    private Long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private int hit;
    @Embedded
    private Address address;
    @Column
    private int rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL)
    private List<HotPlaceComment> comments;
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceImage> images;
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceLike> likes;

    @Builder
    public HotPlace(Long id, String title, String content, int hit, Address address, int rating, Member member, List<HotPlaceComment> comments, List<HotPlaceImage> images, List<HotPlaceLike> likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.address = address;
        this.rating = rating;
        this.member = member;
        this.comments = comments;
        this.images = images;
        this.likes = likes;
    }

    public void update(String title, String content, Address address, int rating, List<HotPlaceImage> hotPlaceImages, List<Long> deleteImageId) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.rating = rating;
        updateHotPlaceImages(hotPlaceImages, deleteImageId);
    }

    private void updateHotPlaceImages(List<HotPlaceImage> hotPlaceImages, List<Long> deleteImageIds) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        for (Long id : deleteImageIds) {
            this.images.removeIf(image -> image.getId().equals(id));
        }
        this.images.addAll(hotPlaceImages);
    }

    public void increaseHit() {
        this.hit++;
    }
}
