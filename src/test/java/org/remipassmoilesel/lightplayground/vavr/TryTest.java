package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.control.Try;
import lombok.val;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class TryTest {

    public static class FakeHttpClient {
        public String get(String url) {
            if (url.contains("nowhere")) {
                throw new RuntimeException("Not found: " + url);
            }
            return "Response body";
        }
    }

    final FakeHttpClient client = new FakeHttpClient();

    @Test(expected = Exception.class)
    public void shouldThrow() {
        client.get("http://nowhere");
    }

    @Test
    public void succeedTry() {
        Try<String> res = Try.ofSupplier(() -> client.get("http://somewhere"));
        assertThat(res.isSuccess(), is(true));
    }
    
    @Test
    public void failedTry() {
        val res = Try.ofSupplier(() -> client.get("http://nowhere"));

        assertThat(res.isFailure(), is(true));

        val value = res.recover(throwable -> Match(throwable).of(
                Case($(instanceOf(RuntimeException.class)), "runtime exception"),
                Case($(), "default value")
        ));
        assertThat(value.get(), equalTo("runtime exception"));
    }

}
