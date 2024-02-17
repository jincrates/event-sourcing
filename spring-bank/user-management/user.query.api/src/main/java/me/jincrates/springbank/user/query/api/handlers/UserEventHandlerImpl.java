package me.jincrates.springbank.user.query.api.handlers;

import lombok.RequiredArgsConstructor;
import me.jincrates.springbank.user.core.events.UserRegisteredEvent;
import me.jincrates.springbank.user.core.events.UserRemovedEvent;
import me.jincrates.springbank.user.core.events.UserUpdatedEvent;
import me.jincrates.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("user-group")
@RequiredArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.user());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.user());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.id());
    }
}
