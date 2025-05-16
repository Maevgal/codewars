package kyu3;

import java.util.List;
import java.util.Map;

public class Solution {
    public static int lastDigit(int[] array) {
        Map<Integer, List<Integer>> sequences = Map.of(
                2, List.of(2, 4, 8, 6),
                3, List.of(3, 9, 7, 1),
                4, List.of(4, 6),
                5, List.of(5),
                6, List.of(6),
                7, List.of(7, 9, 3, 1),
                8, List.of(8, 4, 2, 6),
                9, List.of(9, 1),
                0, List.of(0),
                1, List.of(1)
        );
        if (array.length == 0) {
            return 1;
        } else if (array.length == 1 && array[0] == 0) {
            return 1;
        } else if (array.length == 1) {
            return array[0] % 10;
        } else if (array[0] == 0 && array.length > 2) {
            return 0;
        } else if (array[1] == 0 && array.length == 2) {
            return 1;
        }

        int n1 = array.length >= 1 ? array[0] : 0;
        int n2 = array.length >= 2 ? array[1] : 1;
        int n3 = array.length >= 3 ? array[2] : 1;
        int n4 = array.length >= 4 ? array[3] : 1;
        int n5 = array.length >= 5 ? array[4] : 1;

        if (n5 == 0 && n4 == 0) {
            n4 = 1;
        } else if (n4 == 0) {
            n3 = 1;
        }

        if (n3 == 0 && n2 == 0) {
            return n1 % 10;
        }
        if (n3 == 0) {
            n2 = 1;
        }
        if (n2 == 0) {
            return 1;
        }
        int lastSymbolN1 = n1 % 10;
        int lastSymbolN2 = n2 % 10;
        int period = sequences.get(lastSymbolN1).size();

        if (n2 == 1) {
            return lastSymbolN1;
        }
        switch (period) {
            case 1:
                return sequences.get(lastSymbolN1).get(0);
            case 2:
                if (lastSymbolN2 % 2 == 0) {
                    return sequences.get(lastSymbolN1).get(1);
                } else {
                    return sequences.get(lastSymbolN1).get(0);
                }
            default:
                switch (n2 % 4) {
                    case 0:
                        return (int) (Math.pow(lastSymbolN1, 4) % 10);
                    case 1:
                        return lastSymbolN1;
                    case 2:
                        if (n3 == 1) {
                            return (int) (Math.pow(lastSymbolN1, 2) % 10);
                        } else {
                            return (int) (Math.pow(lastSymbolN1, 4) % 10);
                        }
                    case 3:
                        if (n3 % 2 == 0) {
                            return lastSymbolN1;
                        } else {
                            return (int) (Math.pow(lastSymbolN1, 3) % 10);
                        }
                }

        }
        return 0;
    }
}
