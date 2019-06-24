package rpn.consumer.operator;

import rpn.consumer.Consumer;
import rpn.message.Message;
import rpn.stack.Stack;

public interface Operator extends Consumer {

    void consume(Message message);

    Stack calculate(Stack stack);
}
