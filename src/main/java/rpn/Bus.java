package rpn;

public interface Bus {

    void publish(Message message);
    void subscribe(String eventType, Consumer consumer);
}
