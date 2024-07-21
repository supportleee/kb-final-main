package com.example.kbfinal.dto;

import com.example.kbfinal.exception.UserErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserErrorResponse {
    private UserErrorCode errorCode;
    private String errorMessage;
}
