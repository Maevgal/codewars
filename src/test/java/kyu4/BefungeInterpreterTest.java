package kyu4;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class BefungeInterpreterTest {

    @Test
    public void quine() {
        String code = "01->1# +# :# 0# g# ,# :# 5# 8# *# 4# +# -# _@";

        String expected = code;
        String actual = new BefungeInterpreter().interpret(code);
        assertEquals(expected, actual);
    }

    @Test
    public void sieve() {
        String code = "2>:3g\" \"-!v\\  g30          <\n" +
                      " |!`\"&\":+1_:.:03p>03g+:\"&\"`|\n" +
                      " @               ^  p3\\\" \":<\n" +
                      "2 2345678901234567890123456789012345678";

        String actual = "23571113171923293137";
        String expected = new BefungeInterpreter().interpret(code);
        assertEquals(expected, actual);
    }

    @Test
    public void factorial() {
        String code = "08>:1-:v v *_$.@ \n" +
                      "  ^    _$>\\:^" +
                      "  ^    _$>\\:^";

        String actual = "40320";
        String expected = new BefungeInterpreter().interpret(code);
        assertEquals(expected, actual);
    }

    @Test
    public void helloWorld() {
        String code = ">25*\"!dlroW olleH\":v\n" +
                      "                v:,_@\n" +
                      "                >  ^";
        String actual = "Hello World!\n";
        String expected = new BefungeInterpreter().interpret(code);
        assertEquals(expected, actual);
    }

    @Test
    public void sampleFromDescription() {
        String code = ">987v>.v\nv456<  :\n>321 ^ _@";
        String actual = "123456789";
        String expected = new BefungeInterpreter().interpret(code);
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomDirection() {
        AtomicInteger ones = new AtomicInteger(0);
        AtomicInteger twos = new AtomicInteger(0);
        String testCode = "v@.<\n >1^\n>?<^\n >2^";
        int total = 2000;
        IntStream.range(0, total)
                .forEach(time -> {
                    String result = new BefungeInterpreter().interpret(testCode);
                    assertTrue(result.length() == 1, "result failed for random test");
                    switch (result.charAt(0)) {
                        case '1':
                            ones.incrementAndGet();
                            break;
                        case '2':
                            twos.incrementAndGet();
                            break;
                        default:
                            fail("Code: " + testCode);
                    }
                });
        double result = Math.abs(ones.get() / (double) total - 0.5);
        assertTrue(result < 0.05,
                "Code: <pre>%s</pre> Did not come up with 1s between 45%% and 55%% of the time");
    }
}

/*
* 2>:3g" "-!v\  g30          <
 |!`"&":+1_:.:03p>03g+:"&"`|
 @               ^  p3\" ":<
2 2345678901234567890123456789012345678
*/