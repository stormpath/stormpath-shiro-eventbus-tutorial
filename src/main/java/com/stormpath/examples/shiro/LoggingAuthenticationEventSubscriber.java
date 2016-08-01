package com.stormpath.examples.shiro;


import com.stormpath.examples.shiro.event.AuthenticationEvent;
import org.apache.shiro.event.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An event listener that logs each type of {@link AuthenticationEvent}. <BR/><BR/>
 * This example shows a simple POJO with annotated methods which are discovered when this class is loaded via a
 * <code>shiro.ini</code> (via the {@link org.apache.shiro.config.ReflectionBuilder ReflectionBuilder}.
 */
public class LoggingAuthenticationEventSubscriber {

    final private Logger log = LoggerFactory.getLogger(LoggingAuthenticationEventSubscriber.class);


    @Subscribe
    public void onEvent(AuthenticationEvent.LoginSuccessEvent event) {
        log.debug("Principal '{}' is now logged in.", event.getPrincipal());
    }

    @Subscribe
    public void onEvent(AuthenticationEvent.LoginFailureEvent event) {
        if (log.isTraceEnabled()) {
            log.trace("Principal '{}' failed to login due to the following Exception:", event.getPrincipal(), event.getException());
        }
        else {
            log.debug("Principal '{}' failed to login due to an exception: {}", event.getPrincipal(), event.getException().getMessage());
        }
    }

    @Subscribe
    public void onEvent(AuthenticationEvent.LogoutEvent event) {
        log.debug("Principal '{}' is now logged out.", event.getPrincipal());
    }


}
