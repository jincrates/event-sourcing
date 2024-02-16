package me.jincrates.springbank.user.cmd.api.commonds;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RemoveUserCommand(
    @TargetAggregateIdentifier
    String id
) {

}
