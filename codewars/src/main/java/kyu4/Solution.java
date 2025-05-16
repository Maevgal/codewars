package kyu4;

public class Solution {
    public static String rangeExtraction(int[] arr) {
        StringBuilder result = new StringBuilder();
        if (arr[1] - arr[0] != 1) {
            result.append(arr[0]).append(",");
        } else {
            result.append(arr[0]);
        }

        int firstInt = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] != arr[i - 1] + 1 && arr[i] == arr[i + 1] - 1) {
                result.append(arr[i]);
                firstInt = arr[i];
            } else if (arr[i] == arr[i - 1] + 1 && arr[i] != arr[i + 1] - 1) {
                if (arr[i] - firstInt > 1) {
                    result.append("-").append(arr[i]).append(",");
                } else {
                    result.append(",").append(arr[i]).append(",");
                }
            } else if (arr[i] != arr[i - 1] + 1 && arr[i] != arr[i + 1] - 1) {
                result.append(arr[i]).append(",");
            }
        }

        if (arr[arr.length - 1] - arr[arr.length - 3] == 2) {
            result.append("-").append(arr[arr.length - 1]);

        } else {
            if (result.substring(result.length() - 1).equals(",")) {
                result.append(arr[arr.length - 1]);
            } else {
                result.append(",").append(arr[arr.length - 1]);
            }
        }

        return String.valueOf(result);
    }
}
