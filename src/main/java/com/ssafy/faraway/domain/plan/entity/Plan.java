package com.ssafy.faraway.domain.plan.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Plan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int hit;
    @Column(nullable = false)
    private String tripPlan;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Plan(Long id, String title, String content, int hit, String tripPlan, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.tripPlan = tripPlan;
        this.member = member;
    }

    public void update(String title, String content, String plan) {
        this.title = title;
        this.content = content;
        this.tripPlan = plan;
    }

    public void updateHit() {
        this.hit++;
    }
}
