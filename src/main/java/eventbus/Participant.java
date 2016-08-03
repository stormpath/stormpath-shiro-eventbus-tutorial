package eventbus;

import org.apache.shiro.event.Subscribe;

import static java.lang.System.out;

public class Participant {

    @Subscribe
    public void onEvent(PizzaAvailableEvent event) {
        out.println("nom nom");
    }
}
