package eventbus;

import org.apache.shiro.event.Event;

public class PizzaAvailableEvent extends Event {

    public PizzaAvailableEvent(Object source) {
        super(source);
    }

    public static class PepperoniPizzaAvailableEvent extends PizzaAvailableEvent {

        public PepperoniPizzaAvailableEvent(Object source) {
            super(source);
        }
    }

    public static class VegetablePizzaAvailableEvent extends PizzaAvailableEvent {

        public VegetablePizzaAvailableEvent(Object source) {
            super(source);
        }
    }
}
