package rpn.consumer.operator;

import rpn.bus.Bus;
import rpn.message.Message;
import rpn.message.OperationMessage;
import rpn.message.ResultMessage;
import rpn.stack.Stack;

public class MinusOperator implements Operator {

    private final Bus bus;

    public MinusOperator(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        OperationMessage operationMessage = (OperationMessage)message;
        Stack stack = operationMessage.getStack();

        stack = calculate(stack);

        bus.publish(new ResultMessage(stack));
    }

    @Override
    public Stack calculate(Stack stack) {
        stack.push( -stack.pop() + stack.pop() );
        return stack;
    }
}
