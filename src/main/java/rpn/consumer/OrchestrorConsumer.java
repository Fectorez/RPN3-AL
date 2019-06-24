package rpn.consumer;

import rpn.bus.Bus;
import rpn.message.FinalMessage;
import rpn.message.Message;
import rpn.message.OperationMessage;
import rpn.message.TokenMessage;
import rpn.stack.Stack;
import rpn.stack.StackDelegation;

import static rpn.message.ExpressionMessage.EOE;

public class OrchestrorConsumer implements Consumer {

    private Bus bus;
    private Stack stack = new StackDelegation();

    public OrchestrorConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        TokenMessage tokenMessage = (TokenMessage)message;
        String expressionId = tokenMessage.getExpressionId();
        String token = tokenMessage.getToken();

        if ( EOE.equals(token) ) {
            bus.publish(new FinalMessage(stack.pop()));
        }
        else if ( isNumeric(token) ) {
            stack.push(Double.parseDouble(token));
        }
        else {
            bus.publish(new OperationMessage(stack, token));
        }
    }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
