package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.control.Either;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EitherTest {

    public static class FakeService {

        public Either<String, Object> parseInput(String input) {
            if (input.contains("3")) {
                return Either.left("invalid string");
            }
            return Either.right(input.replaceAll("e", "3"));
        }

    }

    final FakeService service = new FakeService();

    @Test
    public void eitherTest() {
        assertThat(service.parseInput("Hello world !"), equalTo(Either.right("H3llo world !")));
        assertThat(service.parseInput("I want 3 things"), equalTo(Either.left("invalid string")));
    }

}
