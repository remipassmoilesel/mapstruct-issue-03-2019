package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.val;
import org.junit.Test;

import java.net.URI;

public class SneakyThrowsTest {

    @Test
    public void sneakyThrows() {
        val list = List.of("a", "b", "c", "d", "e")
                .map(API.unchecked(letter -> new URI(letter)))
                .toList();

        System.out.println(list);
    }

    @Test
    public void sneakyThrowsTry() {
        val list = List.of("a", "b", "c", "d", "e")
                .map(letter -> Try.ofSupplier(API.unchecked(() -> {
                    if (letter.equals("b")) {
                        throw new Exception(letter);
                    }
                    return new URI(letter);
                })))
                .filter(tryUri -> tryUri.isSuccess())
                .map(tryUri -> tryUri.get())
                .toList();

        System.out.println(list);
    }

}
