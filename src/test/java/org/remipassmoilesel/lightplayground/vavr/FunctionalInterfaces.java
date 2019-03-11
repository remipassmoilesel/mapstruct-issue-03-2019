package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;
import lombok.val;
import org.junit.Test;

import static io.vavr.API.Function;
import static io.vavr.API.None;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

public class FunctionalInterfaces {

    @Test
    public void interfaces() {

        val addAll3 = Function((Integer a, Integer b, Integer c) -> a + b + c);
        val addAll5 = Function5.of((Integer a, Integer b, Integer c, Integer d, Integer e) -> a + b + c + d + e);
        val addAll8 = Function((Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h) -> a + b + c + d + e + f + g + h);

        assertThat(addAll3.apply(1, 1, 1), equalTo(3));
        assertThat(addAll5.apply(1, 1, 1, 1, 1), equalTo(5));
        assertThat(addAll8.apply(1, 1, 1, 1, 1, 1, 1, 1), equalTo(8));
    }

    @Test
    public void composition() {

        val plus5 = Function1.of((Integer a) -> a + 5);
        val minus1 = Function1.of((Integer a) -> a - 1);

        val plus5minus1 = plus5.andThen(minus1);

        assertThat(plus5minus1.apply(8), equalTo(12));
    }

    @Test
    public void lifting() {

        val divide = Function2.of((Integer a, Integer b) -> a / b);
        val safeDivide = Function2.lift(divide);
        val safeDivideTry = Function2.liftTry(divide);

        try {
            divide.apply(5, 0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(ArithmeticException.class));
        }

        assertThat(safeDivide.apply(5, 0), equalTo(None()));
        assertThat(safeDivideTry.apply(5, 0).isFailure(), is(true));
    }

    @Test
    public void partialApplication() {

        val divide = Function2.of((Integer a, Integer b) -> a / b);
        assertThat(divide.apply(25).apply(5), equalTo(5));

        val divide30 = divide.apply(30);
        assertThat(divide30.apply(5), equalTo(6));

    }

    @Test
    public void memoization() {

        val randomOnce = Function0.of(Math::random).memoized();

        val rand1 = randomOnce.apply();
        val rand2 = randomOnce.apply();

        assertThat(rand1, equalTo(rand2));
    }

}
