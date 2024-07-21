package com.example.kbfinal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    NO_USER("사용자 정보가 없습니다."),
    DUPLICATED_USERNAME("username이 중복되는 사용자가 있습니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.")
    ;

    private final String message;
}
