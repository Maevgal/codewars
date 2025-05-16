package kyu7;

/*https://www.codewars.com/kata/56541980fa08ab47a0000040*/
public class Printer {
    public static String printerError(String s) {
        char[] chars = s.toCharArray();
        int exception = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!(chars[i] > 'a') || (!(chars[i] < 'm'))) {
                exception++;
            }
        }

        return exception + "/" + s.length();
    }
}
