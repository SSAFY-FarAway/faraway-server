package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HotPlaceLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_place_like_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceLike(Long id, Member member, HotPlace hotPlace) {
        this.id = id;
        this.member = member;
        this.hotPlace = hotPlace;
    }
}
