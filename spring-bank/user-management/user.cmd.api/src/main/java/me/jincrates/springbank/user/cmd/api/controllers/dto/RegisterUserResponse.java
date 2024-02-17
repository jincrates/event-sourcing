package me.jincrates.springbank.user.cmd.api.controllers.dto;

import lombok.Getter;
import me.jincrates.springbank.user.core.dto.BaseResponse;

@Getter
public class RegisterUserResponse extends BaseResponse {

    private final String id;

    public RegisterUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
