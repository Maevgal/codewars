package kyu4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    /*https://www.codewars.com/kata/5254ca2719453dcc0b00027d*/
    public static List<String> singlePermutations(String s) {
        char[] charArray = s.toCharArray();
        Set<String> res = new HashSet<>();
        List<String> result = new ArrayList<>();
        result.addAll(heapPermutation(charArray, charArray.length, res));
        return result;
    }

    static Set<String> heapPermutation(char[] a, int size, Set<String> result) {
        if (size == 1) {
            result.add(arrayToString(a));
            return result;
        } else {
            for (int i = 0; i < size; i++) {
                heapPermutation(a, size - 1, result);
                if (size % 2 == 1) {
                    char temp = a[0];
                    a[0] = a[size - 1];
                    a[size - 1] = temp;
                } else {
                    char temp = a[i];
                    a[i] = a[size - 1];
                    a[size - 1] = temp;
                }
            }
        }
        return result;
    }

    static String arrayToString(char[] a) {
        StringBuilder str = new StringBuilder();
        for (char c : a) {
            str.append(c);
        }
        return str.toString();
    }
}
