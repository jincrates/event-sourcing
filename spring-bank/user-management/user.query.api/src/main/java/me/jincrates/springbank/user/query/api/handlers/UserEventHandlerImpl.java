package me.jincrates.springbank.user.query.api.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.springbank.user.core.events.UserRegisteredEvent;
import me.jincrates.springbank.user.core.events.UserRemovedEvent;
import me.jincrates.springbank.user.core.events.UserUpdatedEvent;
import me.jincrates.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ProcessingGroup("user-group")
@RequiredArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        log.info("유저 등록 EventHandler id={}", event.id());
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
