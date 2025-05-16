package kuy6;

/*https://www.codewars.com/users/Maevgal/completed_solutions*/
public class Order {
    public static String order(String words) {
        if (words.isEmpty()) {
            return words;
        }

        String[] word = words.split(" ");
        String[] result = new String[word.length];

        for (String string : word) {
            int s = 0;
            for (char ch : string.toCharArray()) {
                if (Character.isDigit(ch)) {
                    int index = Integer.parseInt(String.valueOf(ch));
                    result[index - 1] = string;
                }
            }

        }
        return String.join(", ", result);
    }
}
