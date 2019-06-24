package rpn;

import rpn.bus.Bus;
import rpn.bus.InMemoryBus;
import rpn.consumer.FinalResultConsumer;
import rpn.consumer.OrchestrorConsumer;
import rpn.consumer.TokenizerConsumer;
import rpn.message.ExpressionMessage;
import rpn.consumer.operator.MinusOperator;
import rpn.consumer.operator.PlusOperator;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        Bus bus = new InMemoryBus();
        FinalResultConsumer finalResultConsumer = new FinalResultConsumer();
        OrchestrorConsumer orchestrorConsumer = new OrchestrorConsumer(bus);

        bus.subscribe("expression", new TokenizerConsumer(bus));
        bus.subscribe("token", orchestrorConsumer);
        bus.subscribe("result", orchestrorConsumer);
        bus.subscribe("+", new PlusOperator(bus));
        bus.subscribe("-", new MinusOperator(bus));
        bus.subscribe("final-result", finalResultConsumer);



        System.out.println("About to evaluate '" + expression + "'");
        Double result = evaluate(bus, finalResultConsumer, expression);
        System.out.println("> " + result);
    }

    static Double evaluate(Bus bus, FinalResultConsumer finalResultConsumer, String expression) {
        bus.publish(new ExpressionMessage(expression));
        Optional<Double> optionalResult = finalResultConsumer.findResult();

        return optionalResult.orElse(null);
    }
}
