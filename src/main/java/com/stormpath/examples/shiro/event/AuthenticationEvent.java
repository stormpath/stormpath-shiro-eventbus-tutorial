package com.stormpath.examples.shiro.event;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.event.Event;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * Base class for all AuthenticationEvents.
 */
public class AuthenticationEvent extends Event {

    final private PrincipalCollection principalCollection;

    private AuthenticationEvent(PrincipalCollection principalCollection) {
        super(principalCollection);
        this.principalCollection = principalCollection;
    }

    public PrincipalCollection getPrincipal() {
        return principalCollection;
    }

    public static class LoginSuccessEvent extends AuthenticationEvent {

        public LoginSuccessEvent(PrincipalCollection principalCollection) {
            super(principalCollection);
        }
    }

    public static class LogoutEvent extends AuthenticationEvent {

        public LogoutEvent(PrincipalCollection principalCollection) {
            super(principalCollection);
        }
    }

    public static class LoginFailureEvent extends AuthenticationEvent {

        private AuthenticationException exception;

        public LoginFailureEvent(Object principal, AuthenticationException exception) {
            super(new SimplePrincipalCollection(principal, "unknown"));
            this.exception = exception;
        }

        public AuthenticationException getException() {
            return exception;
        }
    }
}
