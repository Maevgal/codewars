package kyu4;

public class Snail {

    public static int[] snail(int[][] array) {
        if (array[0].length == 0) {
            return new int[0];
        } else {

            int size = array.length * array.length;
            int[] result = new int[size];
            int count = 0;
            int top = 0;
            int bottom = array.length - 1;
            int left = 0;
            int right = array.length - 1;
            while (top <= bottom && left <= right) {
                for (int i = left; i <= right; i++) {
                    result[count] = array[top][i];
                    count++;
                }
                top++;
                for (int i = top; i <= bottom; i++) {
                    result[count] = array[i][right];
                    count++;
                }
                right--;
                if (top <= bottom) {
                    for (int i = right; i >= left; i--) {
                        result[count] = array[bottom][i];
                        count++;
                    }
                    bottom--;
                }
                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        result[count] = array[i][left];
                        count++;
                    }
                    left++;
                }
            }
            return result;
        }
    }
}
