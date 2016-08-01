package com.stormpath.examples.shiro;

import com.stormpath.examples.shiro.event.AuthenticationEvent;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.event.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example {@link EventBus} subscriber, that listens for ALL Shiro generated events, and publishes them on a Guava
 * {@link com.google.common.eventbus.EventBus EventBus}.
 */
public class ShiroToGuavaEventBusBridge implements EventBusAware {

    private EventBus shiroEventBus;

    final private com.google.common.eventbus.EventBus guavaEventBus;

    public ShiroToGuavaEventBusBridge() {
        this.guavaEventBus = getGlobalGuavaEventBus();

        // Since this is an example, we can pretend, there is already guava listeners for this bus.
        this.guavaEventBus.register(new GuavaEventBusSubscriber());
    }


    public void setEventBus(EventBus bus) {
        this.shiroEventBus = bus;

        // @Subscribe methods are intentionally not discovered in EventBusAware implementations, so we must
        // register it directly
        this.shiroEventBus.register(this);
    }

    @Subscribe
    public void bridgeEvent(Object event) {
        this.guavaEventBus.post(event);
    }

    /**
     * This example will just create a new EventBus, but for real world cases, you would use the same instance the rest
     * of your components are using.
     * @return a Guava EventBus
     */
     private com.google.common.eventbus.EventBus getGlobalGuavaEventBus() {

         return new com.google.common.eventbus.EventBus();
     }


    /**
     * Another logging event listener, but this one is subscribed to the guava {@link com.google.common.eventbus.EventBus EventBus}.
     */
    private class GuavaEventBusSubscriber {

        final private Logger log = LoggerFactory.getLogger(GuavaEventBusSubscriber.class);

        @com.google.common.eventbus.Subscribe
        public void onEvent(AuthenticationEvent event) {
            log.info("The event is now on the Guava event bus: {}", event);
        }
    }


}
