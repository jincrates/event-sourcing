package me.jincrates.springbank.user.core.events;

import me.jincrates.springbank.user.core.models.User;

public record UserUpdatedEvent(
    String id,
    User user
) {

}
