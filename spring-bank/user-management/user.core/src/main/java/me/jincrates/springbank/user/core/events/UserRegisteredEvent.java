package me.jincrates.springbank.user.core.events;

import me.jincrates.springbank.user.core.models.User;

public record UserRegisteredEvent(
    String id,
    User user
) {

}
