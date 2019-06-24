package rpn.stack;

public class StackDelegation implements Stack {

    private final java.util.Stack<Double> delegate = new java.util.Stack<>();

    @Override
    public double pop() {
        return delegate.pop();
    }

    @Override
    public void push(double value) {
        delegate.push(value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for ( Double value : delegate ) {
            stringBuilder.append(value).append(" ");
        }
        return stringBuilder.toString();
    }
}
