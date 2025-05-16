package kyu7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/*https://www.codewars.com/kata/5467e4d82edf8bbf40000155*/

public class DescendingOrder {
    public static int sortDesc(final int num) {
        List<Integer> list = new ArrayList<>();

        int temp = num;
        if (num == 0) {
            return num;
        }
        while (temp > 0) {
            list.add(temp % 10);
            temp /= 10;
        }

        Collections.sort(list, Collections.reverseOrder());

        String str = list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining());

        return Integer.parseInt(str);
    }
}
