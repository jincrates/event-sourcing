package me.jincrates.springbank.user.cmd.api.controllers;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.springbank.user.cmd.api.commonds.RegisterUserCommand;
import me.jincrates.springbank.user.cmd.api.controllers.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final CommandGateway commandGateway;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(
        @Validated @RequestBody RegisterUserCommand command
    ) {
        var registerCommand = command.assignId(UUID.randomUUID().toString());
        log.info("[POST] 유저 등록(/api/v1/user/register) id={}", registerCommand.getId());
        try {
            commandGateway.send(registerCommand);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterUserResponse("유저가 등록되었습니다.", registerCommand.getId()));
        } catch (Exception e) {
            var safeErrorMessage = "유저 저장 중에 오류가 발생했습니다.";
            log.warn("{} id={}, errorMessage={}", safeErrorMessage, registerCommand.getId(),
                e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RegisterUserResponse(safeErrorMessage, registerCommand.getId()));
        }
    }
}
