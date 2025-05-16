package kyu4;

import java.util.Arrays;

public class Kata {
    /*https://www.codewars.com/kata/5659c6d896bc135c4c00021e/train/java*/
    public static long nextSmaller(long n) {
        char[] digits = String.valueOf(n).toCharArray();
        int length = digits.length;
        int i = length - 2;
        while (i >= 0 && digits[i] <= digits[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        int j = length - 1;
        while (digits[j] >= digits[i]) {
            j--;
        }

        swap(digits, i, j);

        reverse(digits, i + 1, length - 1);
        System.out.println(digits);
        if (Long.parseLong(String.valueOf(digits[0])) == 0) {
            return -1L;
        }
        long result = Long.parseLong(new String(digits));
        if (result < 0 || result >= n) {
            return -1L;
        }
        return result;
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    public static String sumStrings(String a, String b) {
        String result = "";
        int digit = 0;

        a = trimLeadingZeros(a);
        b = trimLeadingZeros(b);

        if (a.length() > b.length()) {
            if (b.isEmpty()) {
                b = "0";
            }
            int diffLength = a.length() - b.length();
            String padding = "0".repeat(diffLength);
            b = padding + b;
        } else if (b.length() > a.length()) {
            if (a.isEmpty()) {
                a = "0";
            }
            int diffLength = b.length() - a.length();
            String padding = "0".repeat(diffLength);
            a = padding + a;
        }
        int len = a.length() - 1;
        for (int i = len; i >= 0; i--) {
            int digitSum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + digit;
            if (digitSum > 9) {
                digit = 1;
                if (i == 0) {
                    result = digitSum + result;
                } else {
                    result = (digitSum % 10) + result;

                }
            } else {
                digit = 0;
                result = digitSum + result;
            }
        }
        return result;
    }

    public static String trimLeadingZeros(String source) {
        for (int i = 0; i < source.length(); ++i) {
            char c = source.charAt(i);
            if (c != '0') {
                return source.substring(i);
            }
        }
        return "";
    }

    public static String justify(String text, int width) {
        if (text == "") return text;

        String[] words = text.split(" ");
        if (words.length == 1) return text;
        int[] lengths = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            lengths[i] = words[i].length();
        }

        String[] spaces = new String[width];
        String spaceStr = "";
        spaces[0] = " ";
        for (int i = 1; i < spaces.length; i++) {
            spaceStr += " ";
            spaces[i] = spaceStr;
        }

        int start = 0;
        int space = width + 1;
        int allRestSpace = width;
        StringBuilder newText = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (space - lengths[i] - 1 < 0) {
                newText.append(words[start]);
                int numberOfSpaces = i - start - 1;
                for (int j = start + 1; j < i; j++) {
                    int spaceWidth = allRestSpace / numberOfSpaces;
                    spaceWidth += allRestSpace % numberOfSpaces > 0 ? 1 : 0;
                    newText.append(spaces[spaceWidth]);
                    newText.append(words[j]);
                    allRestSpace -= spaceWidth;
                    numberOfSpaces--;
                }
                newText.append("\n");
                start = i;
                space = width - lengths[i];
                allRestSpace = space;
            } else {
                space -= lengths[i] + 1;
                allRestSpace -= lengths[i];
            }
        }
        newText.append(words[start]);
        for (int j = start + 1; j < words.length; j++) {
            newText.append(" ");
            newText.append(words[j]);
        }
        return newText.toString();
    }

    public static long nextBiggerNumber(long n) {
        String str = String.valueOf(n);
        if (str.length() == 1) {
            return -1;
        }

        char[] charArray = str.toCharArray();
        int index = str.length() - 1;
        int changeIndex = 0;
        StringBuilder result = new StringBuilder();

        while (index > 0) {
            if (charArray[index] > charArray[index - 1]) {
                changeIndex = index - 1;
                break;
            }
            index--;
        }

        if (index == 0) {
            return -1;
        }

        for (int i = 0; i < changeIndex; i++) {
            result.append(charArray[i]);
        }

        String strRight = str.substring(changeIndex + 1);
        if (strRight.length() > 1) {
            char[] charArrayRight = str.substring(changeIndex + 1).toCharArray();
            Arrays.sort(charArrayRight);
            char changeElem = 0;
            for (int i = 0; i < charArrayRight.length; i++) {
                if (charArrayRight[i] > charArray[changeIndex]) {
                    changeElem = charArrayRight[i];
                    charArrayRight[i] = charArray[changeIndex];
                    break;
                }
            }
            Arrays.sort(charArrayRight);
            result.append(changeElem);
            for (int i = 0; i < charArrayRight.length; i++) {
                result.append(charArrayRight[i]);
            }
        } else {
            result.append(strRight);
            result.append(charArray[changeIndex]);
        }

        return Long.parseLong(String.valueOf(result));
    }
}

