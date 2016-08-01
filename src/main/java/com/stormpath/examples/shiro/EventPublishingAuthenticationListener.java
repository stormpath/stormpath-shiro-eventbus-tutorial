package com.stormpath.examples.shiro;


import com.stormpath.examples.shiro.event.AuthenticationEvent;
import com.stormpath.examples.shiro.event.AuthenticationEvent.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * This is a simple {@link AuthenticationListener} publishes login success, failures, and logout actions to the
 * EventBus. <BR/>
 * Any class loaded via a shiro.ini (via the {@link org.apache.shiro.config.ReflectionBuilder ReflectionBuilder} can be
 * made {@link EventBusAware}, which is used to initialize the {@link EventBus} instance in this class via the
 * <code>setEventBus</code> method.
 */
public class EventPublishingAuthenticationListener implements AuthenticationListener, EventBusAware {

    private EventBus eventBus;

    public void setEventBus(EventBus bus) {
        this.eventBus = bus;
    }

    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        eventBus.publish(new LoginSuccessEvent(info.getPrincipals()));
    }

    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
        eventBus.publish(new LoginFailureEvent(token.getPrincipal(), ae));
    }

    public void onLogout(PrincipalCollection principals) {
        eventBus.publish(new LogoutEvent(principals));
    }
}
