package com.ssafy.faraway.domain.plan.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Plan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int hit;
    @Column(nullable = false)
    private String travelPlan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanComment> planComments;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanLike> likes;

    @Builder
    public Plan(Long id, String title, String content, int hit, String travelPlan, Member member, List<PlanComment> planComments, List<PlanLike> likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.travelPlan = travelPlan;
        this.member = member;
        this.planComments = planComments;
        this.likes = likes;
    }

    public void update(String title, String content, String travelPlan) {
        this.title = title;
        this.content = content;
        this.travelPlan = travelPlan;
    }

    public void increaseHit() {
        this.hit++;
    }
}
