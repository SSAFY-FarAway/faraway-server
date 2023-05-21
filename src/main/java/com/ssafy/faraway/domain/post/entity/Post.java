package com.ssafy.faraway.domain.post.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private int hit;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> postComments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(Long id, String title, String content, int hit, List<PostComment> postComments, List<Attachment> attachments, List<PostLike> likes, Category category, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.postComments = postComments;
        this.attachments = attachments;
        this.likes = likes;
        this.category = category;
        this.member = member;
    }

    public void update(String title, String content, List<Attachment> attachments, List<Long> deleteAttachmentIds) {
        this.title = title;
        this.content = content;
        updateAttachments(attachments, deleteAttachmentIds);
    }

    private void updateAttachments(List<Attachment> attachments, List<Long> deleteAttachmentIds) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        for (Long id : deleteAttachmentIds) {
            this.attachments.removeIf(attachment -> attachment.getId().equals(id));
        }
        this.attachments.addAll(attachments);
    }

    public void increaseHit() {
        this.hit++;
    }
}
