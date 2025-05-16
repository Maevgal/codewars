package kyu7;

import java.util.ArrayList;
import java.util.List;

/*https://www.codewars.com/kata/55b42574ff091733d900002f*/
public class Kata {
    public static List<String> friend(List<String> x) {
        List<String> result = new ArrayList<>();
        for (String name : x) {
            if (name.length() == 4) {
                result.add(name);
            }
        }
        return result;
    }
}
