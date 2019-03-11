package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Lazy;
import lombok.val;
import org.junit.Test;

import java.util.function.Supplier;

public class LazyTest {

    @Test
    public void supplier() {
        final Supplier<Integer> supplier = () -> {
            System.out.println("Computed several times");
            return 5;
        };

        supplier.get();
        supplier.get();
    }

    @Test
    public void lazy() {
        val lazy = Lazy.of(() -> {
            System.out.println("Computed once only");
            return 5;
        });

        lazy.get();
        lazy.get();
    }


}
