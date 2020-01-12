package hackerrank.projecteuler;

import java.util.Scanner;
import java.math.BigInteger;

/**
 * NDigitFibonacciNumber
 */
public class NDigitFibonacciNumber {

    static void calculateNthDigitFibonacci(int digitLimit, int[] digitToNth) {

        BigInteger previous = BigInteger.valueOf(1L);
        BigInteger current = BigInteger.valueOf(1L);
        int currentDigitCount = 1;
        int previousDigitCount = 1;
        int nth = 3;
        while (currentDigitCount < digitLimit) {
            BigInteger temp = current;
            current = current.add(previous);
            previous = temp;

            previousDigitCount = currentDigitCount;
            currentDigitCount = calculateDigitCount(current);
            if (previousDigitCount != currentDigitCount) {
                digitToNth[currentDigitCount] = nth;
            }
            nth++;

        }

    }

    private static int calculateDigitCount(BigInteger current) {
        return current.toString().length();
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        int[] digitToNth = new int[5001];

        long prev = System.currentTimeMillis();
        calculateNthDigitFibonacci(5000, digitToNth);
        long now = System.currentTimeMillis();
        long diff = now - prev;
        System.out.println("Calculated in " + diff);

        StringBuilder stringBuilder = new StringBuilder();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            stringBuilder.append(digitToNth[n]);
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());

    }

}