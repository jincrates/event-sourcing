package me.jincrates.springbank.user.cmd.api.commonds;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.springbank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;

    public RegisterUserCommand assignId(String uuid) {
        this.id = uuid;
        return this;
    }
}
