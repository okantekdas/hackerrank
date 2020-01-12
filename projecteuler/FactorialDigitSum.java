package hackerrank.projecteuler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * FactorialDigitSum
 */
public class FactorialDigitSum {

    static BigInteger calculateFactorial(int number, HashMap<Integer, BigInteger> valueToFactorial) {
        if (valueToFactorial.get(number) != null) {
            return valueToFactorial.get(number);
        } else if (number == 0) {
            return BigInteger.valueOf(1L);
        } else if (number == 1) {
            return BigInteger.valueOf(1L);
        }

        else {
            BigInteger value1 = calculateFactorial(number - 1, valueToFactorial);
            BigInteger result = value1.multiply(BigInteger.valueOf(number));
            valueToFactorial.put(number, result);
            return result;
        }

    }

    final static BigInteger TEN = BigInteger.valueOf(10L);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        HashMap<Integer, BigInteger> valueToFactorial = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int digitSum = 0;
            BigInteger result = calculateFactorial(n, valueToFactorial);
            while (result.compareTo(BigInteger.valueOf(0L)) != 0) {
                BigInteger remainder = result.mod(TEN);
                result = result.divide(TEN);
                digitSum += remainder.intValue();
            }
            stringBuilder.append(digitSum);
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}
