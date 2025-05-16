package kyu4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

/*https://www.codewars.com/kata/51b66044bce5799a7f000003*/
public class RomanNumerals {
    static Map<String, Integer> help = Map.ofEntries(
                    entry("M", 1000),
                    entry("CM", 900),
                    entry("D", 500),
                    entry("CD", 400),
                    entry("C", 100),
                    entry("XC", 90),
                    entry("L", 50),
                    entry("XL", 40),
                    entry("X", 10),
                    entry("IX", 9),
                    entry("V", 5),
                    entry("IV", 4),
                    entry("I", 1)).entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue()
                    .reversed())
            .collect(Collectors.toMap(Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a, b) -> a,
                    LinkedHashMap::new));


    public static String toRoman(int n) {
        System.out.println(help);
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : help.entrySet()) {
            while (n >= entry.getValue()) {
                result.append(entry.getKey());
                n -= entry.getValue();
            }
        }
        return result.toString();
    }

    public static int fromRoman(String romanNumeral) {
        int result = 0;
        int i = 0;
        while (i != romanNumeral.length()) {
            if (i < romanNumeral.length() - 1) {
                String str = String.valueOf(romanNumeral.charAt(i)) + String.valueOf(romanNumeral.charAt(i + 1));
                String str1 = String.valueOf(romanNumeral.charAt(i));
                if (help.containsKey(str)) {
                    result += help.get(str);
                    i += 2;
                } else if (help.containsKey(str1)) {
                    result += help.get(str1);
                    i++;
                }
            } else {
                String str1 = String.valueOf(romanNumeral.charAt(i));
                result += help.get(str1);
                i++;
            }

        }
        return result;
    }
}
