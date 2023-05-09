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
    @GeneratedValue
    @Column(name = "plan_id")
    private Long id;
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "hit", nullable = false)
    @ColumnDefault("0")
    private Long hit;
    @Column(name = "plan", nullable = false)
    private String plan;
    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false, nullable = false)
    private Member member;

    @Builder
    public Plan(Long id, String title, String content, Long hit, String plan, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.plan = plan;
        this.member = member;
    }

    public void update(String title, String content, String plan) {
        this.title = title;
        this.content = content;
        this.plan = plan;
    }

    public void updateHit() {
        this.hit++;
    }
}
