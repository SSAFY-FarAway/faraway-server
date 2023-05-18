package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    private List<HotPlaceComment> hotPlaceComments;
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceImage> hotPlaceImages;

    @Builder
    public HotPlace(Long id, String title, String content, int hit, Address address, int rating, Member member, List<HotPlaceComment> hotPlaceComments, List<HotPlaceImage> hotPlaceImages) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.address = address;
        this.rating = rating;
        this.member = member;
        this.hotPlaceComments = hotPlaceComments;
        this.hotPlaceImages = hotPlaceImages;
    }

    public void update(String title, String content, Address address, int rating, List<HotPlaceImage> hotPlaceImages) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.rating = rating;
        this.hotPlaceImages.clear();
        this.hotPlaceImages.addAll(hotPlaceImages);
    }

    public void updateHit() {
        this.hit++;
    }
}
