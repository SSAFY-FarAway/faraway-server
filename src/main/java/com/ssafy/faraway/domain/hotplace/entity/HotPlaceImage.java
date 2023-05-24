package com.ssafy.faraway.domain.hotplace.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.common.domain.UploadFile;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotPlaceImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_place_image_id")
    private Long id;
    @Embedded
    private UploadFile uploadFile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceImage(Long id, UploadFile uploadFile, HotPlace hotPlace) {
        this.id = id;
        this.uploadFile = uploadFile;
        this.hotPlace = hotPlace;
    }
}
