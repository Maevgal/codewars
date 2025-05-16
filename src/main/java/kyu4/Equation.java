package kyu4;

import java.math.BigInteger;
import java.util.Arrays;

public class Equation {
    public static BigInteger differentiate(String equation, long x) {
        String[] terms = equation.replace("-", "+-").split("\\+");
        BigInteger res = BigInteger.ZERO;
        for (String term : terms) {
            res = res.add(getTermResult(term, x));
        }
        return res;
    }

    private static BigInteger getTermResult(String part, long x) {
        if (!part.contains("x")) {
            return BigInteger.ZERO;
        }
        part = part.startsWith("x") ? part.replace("x", "1") : part.startsWith("-x") ? part.replace("-x", "-1") : part;
        BigInteger[] arr = Arrays
                .stream(part.replace("x", "").replace("^", " ").split(" "))
                .map(BigInteger::new).toArray(BigInteger[]::new);
        return arr.length == 1 ? arr[0] : arr[0].multiply(arr[1]).multiply(BigInteger.valueOf(x).pow(arr[1].intValue() - 1));
    }
}
