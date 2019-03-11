package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Tuple;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TuplesTest {

    @Test
    public void tuples() {
        val list = Stream.from(5)
                .take(50)
                .map(val -> Tuple.of("a", "b", "c", val))
                .map(tuple4 -> tuple4._1 + " " + tuple4._4)
                .toList();

        assertThat(list.size(), equalTo(50));
    }

}
