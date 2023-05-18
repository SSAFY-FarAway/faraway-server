package com.ssafy.faraway.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultPage<T> {
    private T data;
    private int pageNumber;
    private int pageSize;
    private int pageTotalCnt;
}
