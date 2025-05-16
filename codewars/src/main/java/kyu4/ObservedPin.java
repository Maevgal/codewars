package kyu4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*https://www.codewars.com/kata/5263c6999e0f40dee200059d*/
public class ObservedPin {

    public static List<String> getPINs(String observed) {
        Map<String, List<String>> symbols;
        symbols = Map.of(
                "1", List.of("1", "2", "4"),
                "2", List.of("2", "1", "3", "5"),
                "3", List.of("3", "2", "6"),
                "4", List.of("4", "1", "5", "7"),
                "5", List.of("5", "2", "4", "6", "8"),
                "6", List.of("6", "3", "5", "9"),
                "7", List.of("7", "4", "8"),
                "8", List.of("8", "5", "7", "9", "0"),
                "9", List.of("9", "6", "8"),
                "0", List.of("0", "8")
        );

        List<String> result = new ArrayList<>();
        result.add(observed);

        for (int k = 0; k < observed.length(); k++) {
            String substring = observed.substring(k, k + 1);
            int sizeListResult = result.size();
            for (int j = 1; j < symbols.get(substring).size(); j++) {

                for (int i = 0; i < sizeListResult; i++) {
                    if (k == 0) {
                        String str = symbols.get(substring).get(j) + result.get(i).substring(k + 1);
                        result.add(str);
                    } else if (k < observed.length() - 1) {
                        String str = result.get(i).substring(0, k) + symbols.get(substring).get(j) + result.get(i).substring(k + 1);
                        result.add(str);
                    } else if (k == observed.length() - 1) {
                        String str = result.get(i).substring(0, k) + symbols.get(substring).get(j);
                        result.add(str);
                    }
                }
            }
        }

        return result;
    }
}
