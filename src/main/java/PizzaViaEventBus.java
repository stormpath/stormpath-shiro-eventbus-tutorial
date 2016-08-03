import eventbus.MeetupCoordinator;
import eventbus.Participant;
import eventbus.VegetarianParticipant;
import org.apache.shiro.config.ReflectionBuilder;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;

import java.util.HashMap;
import java.util.Map;

public class PizzaViaEventBus {

    public static void main(String args[]) {

        // everything manually
        EventBus eventBus = new DefaultEventBus();
        MeetupCoordinator coordinator = new MeetupCoordinator();
        coordinator.setEventBus(eventBus);

        Participant joeCoder = new Participant();
        eventBus.register(joeCoder);

        Participant jillCoder = new Participant();
        eventBus.register(jillCoder);

        VegetarianParticipant jackCoder = new VegetarianParticipant();
        eventBus.register(jackCoder);


        coordinator.putPizzaOnTable();

        // and again automatically simulating the shiro.ini,

        ReflectionBuilder builder = new ReflectionBuilder();
        Map<String, String> config = new HashMap<String, String>();
        config.put("coordinator", MeetupCoordinator.class.getName());
        config.put("joeCoder", Participant.class.getName());
        config.put("jillCoder", Participant.class.getName());
        config.put("jackCoder", VegetarianParticipant.class.getName());
        coordinator = (MeetupCoordinator) builder.buildObjects(config).get("coordinator");

        // The above map would translate to:
        /*
        [main]
        coordinator = eventbus.MeetupCoordinator
        joeCode = eventbus.Participant
        jillCoder = eventbus.Participant
        jackCoder = eventbus.egetarianParticipant
         */

        coordinator.putPizzaOnTable();


    }

}
