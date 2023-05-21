package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FindLoginIdDto {
    private String birth;
    private String email;

    @Builder
    public FindLoginIdDto(String birth, String email) {
        this.birth = birth;
        this.email = email;
    }
}
