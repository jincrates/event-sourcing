package me.jincrates.springbank.user.core.dto;

import lombok.Getter;

@Getter
public class BaseResponse {

    private final String message;

    public BaseResponse(String message) {
        this.message = message;
    }
}
