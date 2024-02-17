package me.jincrates.springbank.user.cmd.api.commonds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.jincrates.springbank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RegisterUserCommand(
    @JsonIgnore
    @TargetAggregateIdentifier
    String id,
    User user
) {

    public RegisterUserCommand assignId(String uuid) {
        return new RegisterUserCommand(uuid, this.user);
    }
}
