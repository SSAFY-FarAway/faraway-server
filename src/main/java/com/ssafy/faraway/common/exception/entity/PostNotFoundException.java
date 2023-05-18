package com.ssafy.faraway.common.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostNotFoundException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.POSTS_NOT_FOUND;
}
