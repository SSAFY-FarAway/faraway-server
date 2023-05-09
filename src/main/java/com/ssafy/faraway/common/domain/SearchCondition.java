package com.ssafy.faraway.common.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class SearchCondition {
    private String key;
    private String word;

    @Builder
    public SearchCondition(String key, String word) {
        this.key = key;
        this.word = word;
    }
}
