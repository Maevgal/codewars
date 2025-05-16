package kuy5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Kata {
    /*https://www.codewars.com/kata/52bc74d4ac05d0945d00054e*/
    public static String firstNonRepeatingLetter(String s) {
        String[] split = s.split("");
        List<String> list = new ArrayList<>(Arrays.asList(split));

        List<String> remove = new ArrayList<>();

        while (!list.isEmpty()) {
            List<String> listWithoutFirstSymbol = new ArrayList<>();
            for (int j = 1; j < list.size(); j++) {
                listWithoutFirstSymbol.add(list.get(j));
            }
            if (listWithoutFirstSymbol.contains(list.get(0).toLowerCase()) || listWithoutFirstSymbol.contains(list.get(0).toUpperCase())) {
                remove.add(list.get(0).toLowerCase());
                remove.add(list.get(0).toUpperCase());
                list.removeAll(remove);
            } else {
                return list.get(0);
            }
        }
        return "";
    }

    /*https://www.codewars.com/kata/52e88b39ffb6ac53a400022e*/
    public static String longToIP(long ip) {
        StringBuilder binaryNumber = new StringBuilder();
        while (ip > 0) {
            binaryNumber.append(ip % 2);
            ip /= 2;
        }

        while (binaryNumber.length() < 32) {
            binaryNumber.append(0);
        }

        binaryNumber.reverse();

        String[] octets = new String[4];
        StringBuilder result = new StringBuilder();
        int startIndex = 0;
        int endIndex = 8;
        for (int i = 0; i < octets.length; i++) {
            octets[i] = binaryNumber.substring(startIndex, endIndex);
            startIndex += 8;
            endIndex += 8;
        }


        int decimalNumber = 0;
        int power = 0;
        for (String octet : octets) {
            int binNumber = Integer.parseInt(octet);
            while (binNumber > 0) {
                int temp = binNumber % 10;
                decimalNumber += temp * Math.pow(2, power);
                binNumber = binNumber / 10;
                power++;
            }
            result.append(decimalNumber);
            result.append(".");
            decimalNumber = 0;
            power = 0;
        }

        return result.substring(0, result.length() - 1);
    }

    /*https://www.codewars.com/kata/5511b2f550906349a70004e1/train/java*/
    public static int lastDigit(BigInteger n1, BigInteger n2) {
        if (n1.equals(BigInteger.valueOf(0)) || n2.equals(BigInteger.valueOf(0))) {
            return 1;
        }

        String n1Str = String.valueOf(n1);
        int lastDigitN1 = Integer.parseInt(n1Str.substring(n1Str.length() - 1));
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
        int period = sequences.get(lastDigitN1).size();
        if (period == 1) {
            return sequences.get(lastDigitN1).get(0);
        } else if (period == 2) {
            String n2Str = String.valueOf(n2);
            int lastSymbolN2 = Integer.parseInt(n2Str.substring(n2Str.length() - 1));
            if (lastSymbolN2 % 2 == 0) {
                return sequences.get(lastDigitN1).get(1);
            } else {
                return sequences.get(lastDigitN1).get(0);
            }
        } else {
            int position = n2.mod(BigInteger.valueOf(period)).intValue();
            if (position == 0) {
                return (int) Math.pow(lastDigitN1, period) % 10;
            } else {
                return (int) Math.pow(lastDigitN1, position) % 10;
            }
        }
    }
}
