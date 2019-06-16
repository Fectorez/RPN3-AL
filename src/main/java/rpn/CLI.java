package rpn;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        long result = evaluate(expression);
        System.out.println("> " + result);

        Bus bus = new InMemoryBus();
        bus.subscribe("expression-type", new TokenizerConsumer(bus));
        bus.subscribe("final-result");

        bus.publish(new ExpressionMessage(args[0]));
    }

    static long evaluate(String expression) {
        return 0;
    }
}
