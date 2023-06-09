package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HotPlaceComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "hot_place_comment_id")
    private Long id;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotplace_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceComment(Long id, String content, Member member, HotPlace hotPlace) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.hotPlace = hotPlace;
    }
}
