package hackerrank.projecteuler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * MultiplesOf3And5
 */
public class MultiplesOf3And5 {

    static BigInteger sumMultipliesOf(int limit, HashMap<Integer, BigInteger> limitToSum) {
        BigInteger subtotal = BigInteger.valueOf(0L);
        if (limitToSum != null) {

            int closestToLimit = 0;
            for (int value : limitToSum.keySet()) {
                if (value > closestToLimit && value < limit) {
                    closestToLimit = value;
                }
            }

            if (closestToLimit != 0) {
                subtotal = limitToSum.get(closestToLimit);
            }

            for (int i = closestToLimit + 1; i < limit; i++) {
                if (i % 5 == 0 || i % 3 == 0) {
                    subtotal = subtotal.add(BigInteger.valueOf(i));
                }
                limitToSum.put(i, subtotal);
            }

        }
        return subtotal;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            long a = (n - 1) / 3;
            long b = (n - 1) / 5;
            long c = (n - 1) / 15;

            a = 3 * a * (a + 1) / 2;
            b = 5 * b * (b + 1) / 2;
            c = 15 * c * (c + 1) / 2;
            long result = a + b - c;
            stringBuilder.append(result);
            stringBuilder.append("\n");

        }

        System.out.println(stringBuilder.toString());
    }

}