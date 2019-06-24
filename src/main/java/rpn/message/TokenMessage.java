package rpn.message;

public class TokenMessage implements Message {

    private final String expressionId;
    private final String token;

    public TokenMessage(String expressionId, String token) {
        this.expressionId = expressionId;
        this.token = token;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String eventType() {
        return "token";
    }
}
