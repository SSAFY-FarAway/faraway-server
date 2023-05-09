package com.ssafy.faraway.domain.plan.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PlanLike extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_like_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder
    public PlanLike(Long id, Member member, Plan plan) {
        this.id = id;
        this.member = member;
        this.plan = plan;
    }
}
