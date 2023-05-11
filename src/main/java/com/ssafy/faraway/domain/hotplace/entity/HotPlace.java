package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.hotplace.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "hit", nullable = false)
    private int hit;
    @Embedded
    private Address address;
    @Column(name = "rating")
    private int rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL)
    List<HotPlaceComment> hotPlaceComments;

    @Builder
    public HotPlace(Long id, String title, String content, int hit, Address address, int rating, Member member, List<HotPlaceComment> hotPlaceComments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.address = address;
        this.rating = rating;
        this.member = member;
        this.hotPlaceComments = hotPlaceComments;
    }

    public void update(String title, String content, Address address, int rating) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.rating = rating;
    }

    public void updateHit() {
        this.hit++;
    }

    public List<HotPlaceComment> getHotPlaceComments() {
        return this.hotPlaceComments;
    }
}
