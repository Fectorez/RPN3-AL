package rpn.message;

import rpn.stack.Stack;

public class OperationMessage implements Message {

    private Stack stack;
    private String operationType;

    public OperationMessage(Stack stack, String operationType) {
        this.stack = stack;
        this.operationType = operationType;
    }

    public Stack getStack() {
        return stack;
    }

    @Override
    public String eventType() {
        return operationType;
    }
}
