package kuy6;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Kata {
    /*https://www.codewars.com/kata/523f5d21c841566fde000009*/
    public static int[] arrayDiff(int[] a, int[] b) {
        List<Integer> lista = new ArrayList<>();
        for (int i : a) {
            lista.add(i);
        }

        List<Integer> listb = new ArrayList<>();
        for (int i : b) {
            listb.add(i);
        }

        lista.removeAll(listb);


        int[] result = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            result[i] = lista.get(i);
        }

        return result;
    }

    /*https://www.codewars.com/kata/5679aa472b8f57fb8c000047*/
    public static int findEvenIndex(int[] arr) {
        int total = IntStream.of(arr).sum();

        int right = 0;

        List<Integer> index = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (right == total - (arr[i] + right)) {
                index.add(i);
            }
            right += arr[i];
        }

        if (index.isEmpty()) {
            return -1;
        }

        return index.get(index.size() - 1);

    }
}
