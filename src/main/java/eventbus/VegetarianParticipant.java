package eventbus;

import org.apache.shiro.event.Subscribe;

import static java.lang.System.out;

public class VegetarianParticipant {

    @Subscribe
    public void onEvent(PizzaAvailableEvent.VegetablePizzaAvailableEvent event) {
        out.println("nom nom, vegetables");
    }
}
