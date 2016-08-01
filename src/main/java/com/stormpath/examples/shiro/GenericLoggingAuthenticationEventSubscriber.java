package com.stormpath.examples.shiro;


import com.stormpath.examples.shiro.event.AuthenticationEvent;
import org.apache.shiro.event.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example differs from {@link LoggingAuthenticationEventSubscriber}, instead of subscribing to each type of
 * {@link AuthenticationEvent} individually, all subclasses of {@link AuthenticationEvent} are subscribed automaticly
 * by a single method.
 */
public class GenericLoggingAuthenticationEventSubscriber {

    final private Logger log = LoggerFactory.getLogger(GenericLoggingAuthenticationEventSubscriber.class);

    /**
     * A {@link Subscribe}'d method that will be called any time a event that is an instance of
     * {@link AuthenticationEvent} is published.
     * @param event an event published on login success/failure or logout action.
     */
    @Subscribe
    public void onAnyAuthenticationEvent(AuthenticationEvent event) {
        log.debug("An AuthenticationEvent was fired for: '{}' at timestamp: {}", event.getPrincipal(), event.getTimestamp());
    }
}
