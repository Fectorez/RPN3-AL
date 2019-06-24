package rpn.consumer;

import rpn.message.FinalMessage;
import rpn.message.Message;

import java.util.Optional;

public class FinalResultConsumer implements Consumer {

    private Optional<Double> optionalResult = Optional.empty();

    @Override
    public void consume(Message message) {
        FinalMessage finalMessage = (FinalMessage)message;
        optionalResult = Optional.of(finalMessage.getResult());
    }

    public Optional<Double> findResult() {
        return optionalResult;
    }
}
