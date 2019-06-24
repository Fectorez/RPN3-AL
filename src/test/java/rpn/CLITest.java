package rpn;

import org.junit.Before;
import org.junit.Test;
import rpn.bus.Bus;
import rpn.bus.InMemoryBus;
import rpn.consumer.FinalResultConsumer;
import rpn.consumer.OrchestrorConsumer;
import rpn.consumer.TokenizerConsumer;
import rpn.consumer.operator.MinusOperator;
import rpn.consumer.operator.PlusOperator;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest {

    private Bus bus;
    private FinalResultConsumer resultConsumer;

    @Before
    public void setUp() {
        bus = new InMemoryBus();
        resultConsumer = new FinalResultConsumer();

        bus.subscribe("expression", new TokenizerConsumer(bus));
        bus.subscribe("token", new OrchestrorConsumer(bus));
        bus.subscribe("+", new PlusOperator(bus));
        bus.subscribe("-", new MinusOperator(bus));
        bus.subscribe("final-result", resultConsumer);
    }

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate(bus, resultConsumer, "5")).isEqualTo(5);
    }

    /*@Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
    }*/
}