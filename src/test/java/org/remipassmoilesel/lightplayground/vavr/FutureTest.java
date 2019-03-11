package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.concurrent.Future;
import lombok.val;
import org.junit.Test;

import static io.vavr.API.For;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * WARNING: .get() method on futures block the thread and can throw
 */
public class FutureTest {

    @Test
    public void future() {
        val future1 = Future
                .of(() -> {
                    Thread.sleep(100);
                    return 20;
                })
                .map(tryInt -> tryInt * 2)
                .map(tryInt -> tryInt - 2);

        assertThat(future1.get(), equalTo(38));
    }

    final Function2<Integer, Integer, Future<Integer>> computeInFuture = Function2.of(
            (Integer a, Integer b) -> Future
                    .of(() -> {
                        Thread.sleep(100);
                        return a + b;
                    })
                    .map(tryInt -> tryInt * 2)
                    .map(tryInt -> tryInt - 2)
    );


    @Test
    public void futureAwait() {

        val future1 = computeInFuture.apply(5, 6);
        val future2 = computeInFuture.apply(8, 10);
        val result = future1.zip(future2).get();

        assertThat(result._1, equalTo(20));
        assertThat(result._2, equalTo(34));

    }

    @Test
    public void forComprehension() {

        val futureRes = For(
                computeInFuture.apply(5, 5),
                computeInFuture.apply(10, 10),
                computeInFuture.apply(30, 30)
        ).yield((a, b, c) -> a + b + c).get();

        assertThat(futureRes, equalTo(174));

    }

    @Test
    public void futureSequence() {

        val futures = Future.sequence(
                Stream.from(0).take(10)
                        .map(i -> computeInFuture.apply(i, i))
                        .collect(List.collector())
        );

        val res = futures.get().fold(0, (a, b) -> a + b);
        assertThat(res, equalTo(160));
    }

}
