package rpn.consumer;

import rpn.bus.Bus;
import rpn.message.*;
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
        if ( message instanceof TokenMessage ) {
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
        else if ( message instanceof ResultMessage ) {
            ResultMessage resultMessage = (ResultMessage)message;
            stack = resultMessage.getStack();
        }
        else {
            System.out.println("NE DOIS JAMAIS ARRIVER ICI (problème définition consumers dans CLI ?)");
        }

    }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
