package com.ssafy.faraway.domain.attraction.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AttractionInfo {
    @Id
    @Column(name = "content_id")
    private int contentId;
    @Column(name = "content_type_id", nullable = false)
    private int contentTypeId;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "addr1", length = 100)
    private String addr1;
    @Column(name = "addr2", length = 50)
    private String addr2;
    @Column(name = "zipcode", length = 50)
    private String zipcode;
    @Column(name = "tel", length = 50)
    private String tel;
    @Column(name = "first_image", length = 200)
    private String firstImage;
    @Column(name = "first_image2", length = 200)
    private String firstImage2;
    @Column(name = "readcount")
    private int readCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code", insertable = false, updatable = false)
    private Sido sido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_code", insertable = false, updatable = false)
    private Gugun gugun;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "mlevel")
    private String mLevel;

    @Builder
    public AttractionInfo(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, String firstImage2, int readCount, Sido sido, Gugun gugun, double latitude, double longitude, String mLevel) {
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
        this.latitude = latitude;
        this.longitude = longitude;
        this.mLevel = mLevel;
    }
}
