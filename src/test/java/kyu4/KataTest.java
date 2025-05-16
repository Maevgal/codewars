package kyu4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KataTest {
    Random r = new Random();
    private static long solution(long n){
        String        s    = ""+n;
        char          was  = '9';
        int           i    = s.length();
        StringBuilder next = new StringBuilder(i);

        while (--i >= 0 && s.charAt(i) <= was)                                                 // Seek for the first ascending evolution, coming from the end (right)
            was = s.charAt(i);

        if (i == -1) return -1;

        final char c = s.charAt(i);                                                            // Digit to replace
        char   pivot = (char) s.substring(i+1).chars().filter( x -> x<c ).max().getAsInt();    // "pivot" = second bigger digit...
        int    idx   = s.lastIndexOf(""+pivot);                                                // ... and its index

        next.append( s.substring(0,i) )                                                        // Beginning of the number
                .append( pivot )                                                                   // pivot
                .append( (s.substring(i,idx) + s.substring(idx+1))                                 // Reorder all the remaining digits in descending order
                        .chars()
                        .sorted()
                        .mapToObj( d -> ""+(char) d)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .reverse()
                        .toString() );

        return next.charAt(0)=='0' ? -1 : Long.parseLong(next.toString());
    }

    @DisplayName("Fixed Tests")
    @Order(1)
    @ParameterizedTest(name = "n = {0} ")
    @CsvSource(textBlock = """
      21,12
      907,790
      531,513
      506789,-1
      441,414
      513,351
      351,315
      1027,-1
      153,135
      2071,2017
      1207,1072
      414,144
      1023456789,-1
      123456798,123456789
      10, -1
      30003577, -1
      202233445566,-1
      1234567908,1234567890
      135,-1
      123456789,-1
      9999999999,-1
      51226262651257,51226262627551
      12311133345899,12199854333311
      59884848483559,59884848459853
    """)
    void basicTests(long input, long ans) {
        String errorMsg = ans == -1 ? "Should return -1 if there is no smaller number with same digits" : "Next smaller number from digits of "+ input +" is " + ans;
        Assertions.assertEquals(ans, Kata.nextSmaller(input), errorMsg);
    }

    @DisplayName("Random Tests")
    @Order(2)
    @Test
    void randomTests() {
        for(int i = 0; i < 200; ++i)
        {
            long n = (long)Math.exp(44 * Math.random());
            Assertions.assertEquals(solution(n), Kata.nextSmaller(n), "Failed for n = " + n);
        }
    }

    @DisplayName("Hard Random Tests")
    @Order(3)
    @Test
    void hardRandomTests() {
        Random rand = new Random();
        for(int i = 0; i < 50; ++i)
        {
            long accum = 1;
            int digits = rand.nextInt(13,18);
            int chain = rand.nextInt(9,digits - 2);
            long n = 0;
            int l = 9;
            int floor = 1;
            for(int j = 0 ; j < chain; j ++) {
                int couldDecrease = rand.nextInt(3);
                if(j ==9)
                    floor = 0;
                if(j == 5 && l ==9)
                    l--;
                l =  couldDecrease < 2 ? rand.nextInt(Math.max(floor,l - 3),l + 1) : l;
                n += l * accum;
                accum *= 10;
            }
            n+= rand.nextInt(l + 1, 10)*accum;
            accum *= 10;
            for(int j = chain + 1; j < digits ; j ++) {
                n+= rand.nextInt(1, 10)*accum;
                accum *= 10;
            }
            Assertions.assertEquals(solution(n), Kata.nextSmaller(n), "Failed for n = " + n);
        }
    }
    @Test
    void nextSmaller() {
    }


    @Test
    void test() {

        String[][] tests = {

                {"123", "456","579"},
                {"45", "8797","8842"},
                {"800", "9567","10367"},
                {"99", "1","100"},
                {"00103", "08567","8670"},
                {"", "5","5"},
                {"712569312664357328695151392", "8100824045303269669937","712577413488402631964821329"},
                {"50095301248058391139327916261", "81055900096023504197206408605","131151201344081895336534324866"}
        };

        for(String[] test: tests) {
            String a = test[0];
            String b = test[1];
            String expected = test[2];
            assertEquals(expected, Kata.sumStrings(a, b), String.format("Incorrect answer for inputs:\na=\"%s\"\nb=\"%s\"\n", a, b));
        }
    }
    private String randomNums() {
        int l = r.nextInt(30) + 3;
        String s = "";
        for (int i = 0; i < l; i++) {
            s += r.nextInt(9);
        }
        return s;
    }
    @Test
    void randomTests1() {
        for (int i =0; i < 50; i++) {
            String s = randomNums();
            String s2 = randomNums();
            assertEquals(solve(s, s2), Kata.sumStrings(s, s2), String.format("Incorrect answer for inputs:\na=\"%s\"\nb=\"%s\"\n", s, s2));
        }
    }
    private String solve(String s, String s2) {
        String result = "";
        s2 = "0".repeat(s.length() < s2.length() ? 0: s.length() - s2.length())+s2;
        s = "0".repeat(s2.length() < s.length() ? 0: s2.length() - s.length())+s;
        int remainder = 0;
        for (long i = s.length()-1; i >= 0; i--) {
            int n = Integer.parseInt(s.charAt((int) i) + "");
            n += Integer.parseInt(s2.charAt((int) i) + "");
            n += remainder;
            remainder = n/10;
            result = n%10 + result;
        }
        result = remainder == 0 ? result: remainder + result;
        while (result.startsWith("0")) {
            result = result.substring(1, result.length());
        }
        return result;
    }

    @Test
    void sampleTests() {
        assertEquals("123  45\n6", Kata.justify("123 45 6", 7));
        assertEquals("123", Kata.justify("123", 7));
        assertEquals("", Kata.justify("", 10));
    }

    private static final String LIPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sa"
                                         + "gittis dolor mauris, at elementum ligula tempor eget. In quis rhoncus nunc, at aliquet orci. Fusc"
                                         + "e at dolor sit amet felis suscipit tristique. Nam a imperdiet tellus. Nulla eu vestibulum urna. V"
                                         + "ivamus tincidunt suscipit enim, nec ultrices nisi volutpat ac. Maecenas sit amet lacinia arcu, no"
                                         + "n dictum justo. Donec sed quam vel risus faucibus euismod. Suspendisse rhoncus rhoncus felis at f"
                                         + "ermentum. Donec lorem magna, ultricies a nunc sit amet, blandit fringilla nunc. In vestibulum vel"
                                         + "it ac felis rhoncus pellentesque. Mauris at tellus enim. Aliquam eleifend tempus dapibus. Pellent"
                                         + "esque commodo, nisi sit amet hendrerit fringilla, ante odio porta lacus, ut elementum justo nulla"
                                         + " et dolor.";

    @Test
    void descriptionExample() {
        String expected = "Lorem  ipsum  dolor  sit amet,\nconsectetur  adipiscing  elit.\n"
                          + "Vestibulum    sagittis   dolor\nmauris,  at  elementum  ligula\ntempor  eget.  In quis rhoncus\n"
                          + "nunc,  at  aliquet orci. Fusce\nat   dolor   sit   amet  felis\nsuscipit   tristique.   Nam  a\n"
                          + "imperdiet   tellus.  Nulla  eu\nvestibulum    urna.    Vivamus\ntincidunt  suscipit  enim, nec\n"
                          + "ultrices   nisi  volutpat  ac.\nMaecenas   sit   amet  lacinia\narcu,  non dictum justo. Donec\n"
                          + "sed  quam  vel  risus faucibus\neuismod.  Suspendisse  rhoncus\nrhoncus  felis  at  fermentum.\n"
                          + "Donec lorem magna, ultricies a\nnunc    sit    amet,   blandit\nfringilla  nunc. In vestibulum\n"
                          + "velit    ac    felis   rhoncus\npellentesque. Mauris at tellus\nenim.  Aliquam eleifend tempus\n"
                          + "dapibus. Pellentesque commodo,\nnisi    sit   amet   hendrerit\nfringilla,   ante  odio  porta\n"
                          + "lacus,   ut   elementum  justo\nnulla et dolor.";

        assertEquals(expected, Kata.justify(LIPSUM, 30));
    }

    @Test
    void nextBiggerNumber(){
        assertEquals(21, Kata.nextBiggerNumber(12));
        assertEquals(531, Kata.nextBiggerNumber(513));
        assertEquals(2071, Kata.nextBiggerNumber(2017));
        assertEquals(441, Kata.nextBiggerNumber(414));
        assertEquals(414, Kata.nextBiggerNumber(144));
        assertEquals(19009, Kata.nextBiggerNumber(10990));
        assertEquals(-1, Kata.nextBiggerNumber(9));
        assertEquals(-1, Kata.nextBiggerNumber(111));
        assertEquals(-1, Kata.nextBiggerNumber(531));

    }

}