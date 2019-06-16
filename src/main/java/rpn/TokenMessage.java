package rpn;

public class TokenMessage implements Message {

    private final String expressionId;
    private final Token token;

    public TokenMessage(String expressionId, String tokenStr) {
        this.expressionId = expressionId;
        this.token = new Token(tokenStr);
    }

    @Override
    public String eventType() {
        return "token";
    }
}
