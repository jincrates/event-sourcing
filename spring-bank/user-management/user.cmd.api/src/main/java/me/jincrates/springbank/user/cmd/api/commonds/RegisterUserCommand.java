package me.jincrates.springbank.user.cmd.api.commonds;

import me.jincrates.springbank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RegisterUserCommand(
    @TargetAggregateIdentifier
    String id,
    User user
) {

}
