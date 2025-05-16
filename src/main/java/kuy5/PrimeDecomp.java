package kuy5;

/*https://www.codewars.com/kata/54d512e62a5e54c96200019e*/
public class PrimeDecomp {
    public static String factors(int n) {
        StringBuilder result = new StringBuilder();
        int a = 2;
        int count = 0;
        while (n >= a) {
            if (n % a == 0) {
                if (n == a) {
                    count++;
                    if (count == 1) {
                        result.append("(").append(a).append(")");
                    } else {
                        result.append("(").append(a).append("**").append(count).append(")");
                    }
                    break;
                } else {
                    count++;
                    n = n / a;
                }
            } else {
                if (count != 0) {
                    if (count == 1) {
                        result.append("(").append(a).append(")");
                    }
                    if (count > 1) {
                        result.append("(").append(a).append("**").append(count).append(")");
                    }
                }
                a++;
                count = 0;
            }
        }
        return result.toString();
    }
}
