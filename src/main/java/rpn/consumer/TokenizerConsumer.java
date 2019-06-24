package rpn.consumer;

import rpn.bus.Bus;
import rpn.message.ExpressionMessage;
import rpn.message.Message;
import rpn.message.TokenMessage;

import java.util.stream.Stream;

import static rpn.message.ExpressionMessage.EOE;

public class TokenizerConsumer implements Consumer {

    private final Bus bus;

    public TokenizerConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        ExpressionMessage expressionMessage = (ExpressionMessage) message;
        String expression = expressionMessage.expression();

        Stream
            .of(expression.split("\\s+"))
            .forEach(token -> bus.publish(new TokenMessage(expressionMessage.expressionId(),token)) );

        bus.publish(new TokenMessage(expressionMessage.expressionId(),EOE));
    }
}
