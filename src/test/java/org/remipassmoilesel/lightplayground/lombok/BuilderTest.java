package org.remipassmoilesel.lightplayground.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Log // exists also @slf4j, log4j ...
public class BuilderTest {

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true)
    static class ToBuild {
        private final String arg1;
        private final String arg2;
    }

    @Test
    public void simple() {
        val built = ToBuild.builder().arg1("value1").arg2("value2").build();
        assertThat(built.getArg1(), equalTo("value1"));
    }

    @Test
    public void toBuild() {
        val built = ToBuild.builder().arg1("value1").arg2("value2").build();
        assertThat(built.getArg1(), equalTo("value1"));

        val built2 = built.toBuilder().arg2("value3").build();
        assertThat(built2.getArg2(), equalTo("value3"));
    }

}
