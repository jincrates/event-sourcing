package me.jincrates.springbank.user.query.api.handlers;

import me.jincrates.springbank.user.core.events.UserRegisteredEvent;
import me.jincrates.springbank.user.core.events.UserRemovedEvent;
import me.jincrates.springbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
