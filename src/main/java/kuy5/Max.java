package kuy5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*https://www.codewars.com/kata/54521e9ec8e60bc4de000d6c*/
public class Max {

    public static int sequence(int[] arr) {
        int negative = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < 0) {
                negative++;
            }
        }

        if (arr.length == 0 || negative == arr.length - 1) {
            return 0;
        }

        Set<List<Integer>> sequences = new HashSet<>();

        int k = 0;
        while (k <= arr.length) {
            for (int i = k; i <= arr.length; i++) {
                List<Integer> sequence = new ArrayList<>();
                int j = k;
                while (j < i) {
                    sequence.add(arr[j]);
                    sequences.add(sequence);
                    j++;
                }

            }
            k++;
        }
        System.out.println(sequences);

        List<Integer> sum = new ArrayList<>();

        for (List<Integer> seq : sequences) {
            int s = 0;
            for (Integer num : seq) {

                s += num;
            }
            sum.add(s);
        }

        int max = 0;
        max = Collections.max(sum);

        return max;
    }

    public static int sequenceKadane(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxSum = 0;
        int currentSum = 0;
        int start = 0;
        int end = 0;
        int tempStart = 0;
        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum += arr[i];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        return maxSum;
    }
}
