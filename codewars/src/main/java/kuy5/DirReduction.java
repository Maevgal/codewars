package kuy5;

import java.util.Stack;
/*https://www.codewars.com/kata/550f22f4d758534c1100025a*/
public class DirReduction {
    public static String[] dirReduc(String[] arr) {
        Stack<String> sec = new Stack<>();

        int i = 0;
        String[] result;
        while (i < arr.length - 1) {
            if (arr[i].equals("WEST") && arr[i + 1].equals("EAST") ||
                arr[i].equals("EAST") && arr[i + 1].equals("WEST") ||
                arr[i].equals("NORTH") && arr[i + 1].equals("SOUTH") ||
                arr[i].equals("SOUTH") && arr[i + 1].equals("NORTH")) {

                i = i + 2;
            } else {
                if (sec.isEmpty()) {
                    sec.push(arr[i]);
                    i++;
                } else {
                    if (sec.peek().equals("WEST") && arr[i].equals("EAST") ||
                        sec.peek().equals("EAST") && arr[i].equals("WEST") ||
                        sec.peek().equals("NORTH") && arr[i].equals("SOUTH") ||
                        sec.peek().equals("SOUTH") && arr[i].equals("NORTH")) {
                        sec.pop();
                        i++;
                    } else {
                        sec.push(arr[i]);
                        i++;
                    }
                }
            }
        }

        if (sec.isEmpty()) {
            sec.push(arr[i]);
        } else if (arr[arr.length - 1].equals("WEST") && sec.peek().equals("EAST") ||
                   arr[arr.length - 1].equals("EAST") && sec.peek().equals("WEST") ||
                   arr[arr.length - 1].equals("NORTH") && sec.peek().equals("SOUTH") ||
                   arr[arr.length - 1].equals("SOUTH") && sec.peek().equals("NORTH")) {
            sec.pop();
        } else {
            sec.push(arr[arr.length - 1]);
        }


        int size = sec.size();
        result = new String[size];
        for (int j = 0; j < size; j++) {
            result[j] = sec.get(j);
        }
        return result;
    }


}
