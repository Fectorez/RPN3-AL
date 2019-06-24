package rpn.message;

import java.util.UUID;

public class ExpressionMessage implements Message {

    public static final String EOE = "EOE";

    private final String expression;
    private final String expressionId;

    public ExpressionMessage(String expression, String expressionId) {
        this.expression = expression;
        this.expressionId = expressionId;
    }

    public ExpressionMessage(String expression) {
        this(expression, UUID.randomUUID().toString());
    }

    @Override
    public String eventType() {
        return "expression";
    }

    public String expression() {
        return expression;
    }

    public String expressionId() {
        return expressionId;
    }
}
