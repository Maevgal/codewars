package kyu4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BefungeInterpreter {
    public static String interpret(String code) {

        List<String> split = List.of(code.split("\n"));

        List<List<String>> matrix = new ArrayList<>();
        for (String s : split) {
            List<String> strings = new ArrayList<>(List.of(s.split("")));
            matrix.add(strings);
        }


        int x = 0;
        int y = 0;
        String element = matrix.get(x).get(y);
        String direction = ">";

        Stack<Integer> result = new Stack<>();
        StringBuilder str = new StringBuilder();
        while (!element.equals("@")) {

            if (element.matches("[0-9]")) {
                result.push(Integer.valueOf(element));
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("+")) {
                Integer a = result.pop();
                Integer b = result.pop();
                result.push(a + b);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("-")) {
                Integer a = result.pop();
                Integer b = result.pop();
                result.push(b - a);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("*")) {
                Integer a = result.pop();
                Integer b = result.pop();
                result.push(b * a);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                    case "?" -> x++;
                }
            } else if (element.equals("/")) {
                Integer a = result.pop();
                Integer b = result.pop();
                result.push((int) Math.floor(b / a));
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("%")) {
                Integer a = result.pop();
                Integer b = result.pop();
                result.push(b % a);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("!")) {
                int a = result.pop();
                if (a == 0) {
                    result.push(1);
                } else {
                    result.push(0);
                }
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("`")) {
                int a = result.pop();
                int b = result.pop();
                if (b > a) {
                    result.push(1);
                } else {
                    result.push(0);
                }
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals(">")) {
                direction = ">";
                y++;
            } else if (element.equals("<")) {
                direction = "<";
                y--;
            } else if (element.equals("^")) {
                direction = "^";
                x--;
            } else if (element.equals("v")) {
                direction = "v";
                x++;
            } else if (element.equals("?")) {

                String[] myArray = {">", "<", "^", "v"};
                Random rand = new Random();


                direction = myArray[rand.nextInt(myArray.length)];
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("_")) {
                int a = result.pop();
                if (a == 0) {
                    y++;
                    direction = ">";
                } else {
                    y--;
                    direction = "<";
                }
            } else if (element.equals("|")) {
                int a = result.pop();
                if (a == 0) {
                    x++;
                } else x--;
            } else if (element.equals("\"")) {
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
                element = matrix.get(x).get(y);
                while (!element.equals("\"")) {
                    result.push((int) element.charAt(0));
                    switch (direction) {
                        case ">" -> y++;
                        case "<" -> y--;
                        case "^" -> x--;
                        case "v" -> x++;
                    }
                    element = matrix.get(x).get(y);
                }
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals(":")) {
                if (result.isEmpty()) {
                    result.push(0);
                } else {
                    Integer peek = result.peek();
                    result.push(peek);
                }
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("\\")) {
                int a = result.pop();
                int b = 0;
                if (!result.isEmpty()) {
                    b = result.pop();
                }
                result.push(a);
                result.push(b);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("$")) {
                result.pop();
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals(".")) {

                int a = result.pop();


                str.append(a);

                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals(",")) {
                int a = result.pop();
                if (a > 9) {
                    str.append((char) a);
                } else {
                    str.append(a);
                }

                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("#")) {
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("p")) {
                int a = result.pop();
                int b = result.pop();
                int v = result.pop();
                matrix.get(a).set(b, String.valueOf(v));
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals("g")) {
                int a = result.pop();
                int b = result.pop();
                int v = 0;
                char vr = matrix.get(a).get(b).charAt(0);
                List<Character> mas = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
                if (!mas.contains(vr)) {
                    v = (int) matrix.get(a).get(b).charAt(0);
                } else {
                    v = Integer.parseInt(matrix.get(a).get(b));
                }

                result.push(v);
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            } else if (element.equals(" ")) {
                switch (direction) {
                    case ">" -> y++;
                    case "<" -> y--;
                    case "^" -> x--;
                    case "v" -> x++;
                }
            }
            element = matrix.get(x).get(y);
        }
        return String.valueOf(str);
    }

    public static void direction(String direction, int x, int y) {

    }


}
