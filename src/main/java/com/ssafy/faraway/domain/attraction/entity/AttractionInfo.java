package com.ssafy.faraway.domain.attraction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionInfo {
    @Id
    @Column(name = "content_id")
    private int contentId;
    @Column(nullable = false)
    private int contentTypeId;
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String addr1;
    @Column(length = 50)
    private String addr2;
    @Column(length = 50)
    private String zipcode;
    @Column(length = 50)
    private String tel;
    @Column(length = 200)
    private String firstImage;
    @Column(name = "first_image2", length = 200)
    private String firstImage2;
    @Column(name = "readcount")
    private int readCount;
    @ManyToOne
    @JoinColumn(name = "sido_code")
    private Sido sido;
    @ManyToOne
    @JoinColumn(name = "gugun_code")
    private Gugun gugun;
    @OneToOne(mappedBy = "attractionInfo")
    private AttractionDesc attractionDesc;
    @OneToMany(mappedBy = "attractionInfo")
    private List<AttractionLike> likes;
    private double latitude;
    private double longitude;
    @Column(name = "mlevel")
    private String mLevel;

    @Builder
    public AttractionInfo(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, String firstImage2, int readCount, Sido sido, Gugun gugun, AttractionDesc attractionDesc, List<AttractionLike> likes, double latitude, double longitude, String mLevel) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readCount = readCount;
        this.sido = sido;
        this.gugun = gugun;
        this.attractionDesc = attractionDesc;
        this.likes = likes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mLevel = mLevel;
    }
}
