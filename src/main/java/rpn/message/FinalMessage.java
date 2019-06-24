package rpn.message;

public class FinalMessage implements Message {

    private final double result;

    public FinalMessage(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String eventType() {
        return "final-result";
    }
}
