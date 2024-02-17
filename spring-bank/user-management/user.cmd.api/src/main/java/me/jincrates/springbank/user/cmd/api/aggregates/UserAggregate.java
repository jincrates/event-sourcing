package me.jincrates.springbank.user.cmd.api.aggregates;

import me.jincrates.springbank.user.cmd.api.commonds.RegisterUserCommand;
import me.jincrates.springbank.user.cmd.api.commonds.RemoveUserCommand;
import me.jincrates.springbank.user.cmd.api.commonds.UpdateUserCommand;
import me.jincrates.springbank.user.cmd.api.security.PasswordCrypt;
import me.jincrates.springbank.user.cmd.api.security.PasswordCryptImpl;
import me.jincrates.springbank.user.core.events.UserRegisteredEvent;
import me.jincrates.springbank.user.core.events.UserRemovedEvent;
import me.jincrates.springbank.user.core.events.UserUpdatedEvent;
import me.jincrates.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordCrypt passwordCrypt = new PasswordCryptImpl();

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.user().assignId(command.id());
        var hashedPassword = passwordCrypt.encodedPassword(newUser.getAccount().getPassword());
        newUser.getAccount().setPassword(hashedPassword);

        var event = new UserRegisteredEvent(command.id(), newUser);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updateUser = command.user().assignId(command.id());
        var hashedPassword = passwordCrypt.encodedPassword(updateUser.getAccount().getPassword());
        updateUser.getAccount().setPassword(hashedPassword);

        var event = new UpdateUserCommand(command.id(), command.user());
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent(command.id());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.id();
        this.user = event.user();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.user();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
