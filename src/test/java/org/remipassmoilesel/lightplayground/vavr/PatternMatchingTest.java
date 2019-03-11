package org.remipassmoilesel.lightplayground.vavr;

import lombok.val;
import org.junit.Test;

import static io.vavr.Predicates.instanceOf;
import static io.vavr.API.*;
import static io.vavr.Predicates.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PatternMatchingTest {

    @Test
    public void instanceOfWithDefault() {
        final Object value = "a";

        val type = Match(value).of(
                Case($(instanceOf(String.class)), "string"),
                Case($(instanceOf(Integer.class)), "integer"),
                Case($(instanceOf(Long.class)), "long"),
                Case($("b"), "b"),
                Case($(), "default")
        );

        assertThat(type, equalTo("string"));
    }

    @Test
    public void isInRange() {
        final Object value = 2;

        val range = Match(value).of(
                Case($(isIn(1, 2)), "one or two"),
                Case($(isIn(3, 4)), "three or four")
        );

        assertThat(range, equalTo("one or two"));
    }

    @Test
    public void multiple() {
        final Object value = 2;

        val range = Match(value).of(
                Case($(allOf(isNotNull(), isIn(1, 2))), "allOf one or two"),
                Case($(anyOf(isNotNull(), isIn(1, 2))), "anyOf one or two"),
                Case($(noneOf(isNull(), isIn(1, 2))), "noneOf one or two")
        );

        assertThat(range, equalTo("allOf one or two"));
    }

    @Test
    public void multipleMatch() {
        final Integer value = 2;

        Integer range = Match(value).of(
                Case($(2), val -> val + 2),
                Case($(2), val -> val + 5)
        );

        assertThat(range, equalTo(4));
    }

}
