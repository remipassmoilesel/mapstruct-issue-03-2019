package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.val;
import org.junit.Test;

import static io.vavr.API.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OptionsTest {

    @Test
    public void handlers() {
        Option.of(null)
                .peek(value -> System.out.println(String.format("Value is present %s", value)))
                .onEmpty(() -> System.out.println("No value present"));
    }

    @Test
    public void map() {
        Option.of("test string")
                .map(value -> value.toUpperCase());
    }

    @Test
    public void list() {
        List<Object> nonNullValues = List.of(Option.of(null), Option.of(1), Option.of(2), Option.of("e"))
                .flatMap(o -> o.toStream());
        assertThat(nonNullValues.size(), equalTo(3));
    }

    @Test
    public void patternMatching() {

        val match = Function(value -> Match(value).option(
                Case($(0), "zero"),
                Case($(1), "one"),
                Case($(2), "two")
                )
        );

        assertThat(match.apply(null).isDefined(), equalTo(false));
        assertThat(match.apply(0).get(), equalTo("zero"));
        assertThat(match.apply(1).get(), equalTo("one"));

    }

}
