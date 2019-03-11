package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;
import lombok.val;
import org.junit.Test;

import java.util.Iterator;

import static io.vavr.API.For;
import static io.vavr.API.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IteratorTest {

    @Data
    @Builder
    static class Person {
        private String name;
        private List<Tweet> tweet;
    }

    @Data
    @Builder
    static class Tweet {
        private String content;
    }

    @Test
    public void iteratorTest() {
        val testList = List(
                Person.builder().name("Person 1").tweet(List(Tweet.builder().content("Tweet 1").build(), Tweet.builder().content("Tweet 2").build())).build(),
                Person.builder().name("Person 2").tweet(List(Tweet.builder().content("Tweet 3").build(), Tweet.builder().content("Tweet 4").build())).build()
        );

        Iterator<String> iterator =
                For(testList, person ->
                        For(person.getTweet()).yield(tweets ->
                                person.name + "," + tweets.content
                        )
                );

        assertThat(iterator.next(), equalTo("Person 1,Tweet 1"));
        assertThat(iterator.next(), equalTo("Person 1,Tweet 2"));
    }
}
