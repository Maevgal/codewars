package kuy6;

import java.util.ArrayList;
import java.util.List;

/*https://www.codewars.com/kata/54da5a58ea159efa38000836*/
public class FindOdd {
    public static int findIt(int[] a) {
        List<Integer> list = new ArrayList<>();
        for (int k : a) {
            list.add(k);
        }

        List<Integer> remove = new ArrayList<>();
        int loop = 1;
        while (!list.isEmpty()) {
            int j = 0;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(j).equals(list.get(i))) {
                    loop += 1;
                }
            }
            if (loop % 2 == 0) {
                loop = 1;
                remove.add(list.get(j));
                list.removeAll(remove);
            } else {
                return list.get(j);
            }
        }
        return a[0];
    }
}

