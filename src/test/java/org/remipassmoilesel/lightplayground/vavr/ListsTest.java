package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.Test;

import java.util.Arrays;

public class ListsTest {

    @Test
    public void listsToJava() {
        val list = List.of(1, 2, 3).asJava();
        val list2 = List.of(1, 2, 3).asJavaMutable();
        val list3 = List.of(1, 2, 3).toJavaList(); // far less performant, see jmh playground
    }

    @Test
    public void listsFromJava() {
        val javaList = Arrays.asList(1, 2, 3, 4, 5, 6);

        val vavrList = Stream.ofAll(javaList.stream()).map(i -> i * 2).toList();
        val vavrList2 = Stream.ofAll(javaList).map(i -> i * 2).toList();

    }

}
