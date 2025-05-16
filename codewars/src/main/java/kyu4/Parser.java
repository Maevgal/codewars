package kyu4;

import java.util.Map;

import static java.util.Map.entry;

/*https://www.codewars.com/kata/525c7c5ab6aecef16e0001a5*/
public class Parser {
        public static int parseInt(String numStr) {
        Map<String, Integer> numerals = Map.ofEntries(
                entry("one", 1),
                entry("two", 2),
                entry("three", 3),
                entry("four", 4),
                entry("five", 5),
                entry("six", 6),
                entry("seven", 7),
                entry("eight", 8),
                entry("nine", 9),
                entry("ten", 10),
                entry("eleven", 11),
                entry("twelve", 12),
                entry("thirteen", 13),
                entry("fifteen", 15),
                entry("zero", 0),
                entry("thir", 3),
                entry("fif", 5),
                entry("twen", 2),
                entry("for", 4),
                entry("eigh", 8));

        String[] split = numStr.split("[-\s]+");

        int result = 0;
        StringBuilder bigResult = new StringBuilder();
        for (String s : split) {

            if (numerals.containsKey(s)) {
                result += numerals.get(s);
            } else if (s.equals("and")) {

            } else if (s.substring(s.length() - 2).equals("ty")) {
                int a = numerals.get(s.substring(0, s.length() - 2));
                result += a * 10;
            } else if (s.substring(s.length() - 4).equals("teen")) {
                int a = numerals.get(s.substring(0, s.length() - 4));
                result += a + 10;
            } else if (s.equals("hundred")) {
                result *= 100;
            } else if (s.equals("thousand")) {
                if (split[split.length - 1].equals(s)) {
                    result *= 1000;
                } else {
                    bigResult.append(result);
                    result = 0;
                }
            } else if (s.equals("million")) {
                result *= 1000000;
            }
        }

        if (bigResult.isEmpty()) {
            return result;
        } else {
            if (result > 99) {
                bigResult.append(result);
            } else if (result > 9) {
                bigResult.append("0");
                bigResult.append(result);
            } else {
                bigResult.append("00");
                bigResult.append(result);
            }

            return Integer.parseInt(String.valueOf(bigResult));
        }

    }
}
