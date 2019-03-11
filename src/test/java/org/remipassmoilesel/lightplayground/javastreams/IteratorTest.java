package org.remipassmoilesel.lightplayground.javastreams;

import com.google.common.collect.Streams;
import lombok.val;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IteratorTest {

    @Test
    public void iterator() {
        val testList = IntStream.range(0, 2000).boxed().collect(Collectors.toList());
        val testIterator = testList.iterator();

        val first = Streams.stream(testIterator).limit(100).collect(Collectors.toList());
        assertThat(first.get(0), equalTo(0));

        val second = Streams.stream(testIterator).limit(100).collect(Collectors.toList());
        assertThat(second.get(0), equalTo(100));

    }
}
