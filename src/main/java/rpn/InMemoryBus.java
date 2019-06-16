package rpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBus implements Bus {

    private final Map<String, List<Consumer>> consumers = new HashMap<>();

    @Override
    public void publish(Message message) {
        List<Consumer> consumers = this.consumers.get(message.eventType());
        if ( consumers == null ) {
            System.out.println("WARNING: \"" + message.eventType() + "\" message not consumed.");
            return;
        }
        consumers.forEach(c -> c.consume(message));
    }

    @Override
    public void subscribe(String eventType, Consumer consumer) {
        List<Consumer> consumers = this.consumers.get(eventType);

        if ( consumers == null ) {
            consumers = new ArrayList<>();
            this.consumers.put(eventType, consumers);
        }

        consumers.add(consumer);
    }
}
