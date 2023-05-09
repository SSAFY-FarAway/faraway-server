package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HotPlace extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "hot_place_id")
    private Long id;
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "hit", nullable = false)
    @ColumnDefault("0")
    private Long hit;
    @Embedded
    private Address address;
    @Column(name = "rating")
    private Integer rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public HotPlace(Long id, String title, String content, Long hit, Address address, Integer rating, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.address = address;
        this.rating = rating;
        this.member = member;
    }
}
