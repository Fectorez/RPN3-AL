package rpn.message;

import rpn.stack.Stack;

public class ResultMessage implements Message {

    private Stack stack;

    public ResultMessage(Stack stack) {
        this.stack = stack;
    }

    public Stack getStack() {
        return stack;
    }

    @Override
    public String eventType() {
        return "result";
    }
}
