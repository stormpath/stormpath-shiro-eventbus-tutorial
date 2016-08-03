package eventbus;

import eventbus.PizzaAvailableEvent.*;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;


public class MeetupCoordinator  implements EventBusAware {
    private EventBus eventBus;

    public void putPizzaOnTable() {
        eventBus.publish(new PepperoniPizzaAvailableEvent(this));
        eventBus.publish(new VegetablePizzaAvailableEvent(this));
    }

    public void setEventBus(EventBus bus) {
        this.eventBus = bus;
    }
}
