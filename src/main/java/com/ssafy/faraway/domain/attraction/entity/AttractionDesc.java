package com.ssafy.faraway.domain.attraction.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_description")
public class AttractionDesc {
    @Id
    @Column(name = "content_id")
    private int contentId;
    @Column(length = 100)
    private String homepage;
    @Column(length = 10000)
    private String overview;
    @Column(length = 45, name = "telname")
    private String telName;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Builder
    public AttractionDesc(int contentId, String homepage, String overview, String telName, AttractionInfo attractionInfo) {
        this.contentId = contentId;
        this.homepage = homepage;
        this.overview = overview;
        this.telName = telName;
        this.attractionInfo = attractionInfo;
    }
}
