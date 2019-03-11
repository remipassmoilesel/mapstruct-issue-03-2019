package org.remipassmoilesel.lightplayground.lombok;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.Test;

@Log // exists also @slf4j, log4j ...
public class BasicsTest {

    @Test
    public void basics() {
        log.info("Hey !");
    }

    @Test(expected = Exception.class)
    @SneakyThrows
    public void sneaky() {
        throw new Exception();
    }

}
