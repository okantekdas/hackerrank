package hackerrank.projecteuler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * EvenFibonacciNumbers
 */
public class EvenFibonacciNumbers {

    static long calculateFibonacci(HashMap<Long, Long> valueToResults, long n) {
        if (n == 0) {
            valueToResults.put(0L, 0L);
            return 0L;
        } else if (n == 1) {
            valueToResults.put(1L, 1L);
            return 1L;
        } else if (valueToResults.get(n) != null) {
            return valueToResults.get(n);
        } else {
            long value1 = calculateFibonacci(valueToResults, n - 2);
            valueToResults.put(n - 2, value1);
            long value2 = calculateFibonacci(valueToResults, n - 1);
            valueToResults.put(n - 1, value2);
            return value1 + value2;

        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        final HashMap<Long, Long> valueToResults = new HashMap<>();
        final HashMap<Long, ArrayList<Long>> valueToPrimeSubtotals = new HashMap<>();

        for (int a0 = 0; a0 < t; a0++) {
            long n = in.nextLong();
            // System.out.println("Results: " + calculateFibonacci(valueToResults, n + 1));

            long closestValue = 0L;
            long closestIndex = 0L;
            long subtotal = 0L;
            if (valueToPrimeSubtotals != null) {
                for (long val : valueToPrimeSubtotals.keySet()) {
                    if (val < n && val > closestValue) {
                        closestValue = val;
                        subtotal = valueToPrimeSubtotals.get(closestValue).get(1);
                        closestIndex = valueToPrimeSubtotals.get(closestValue).get(0);
                    }

                }
            }
            long j = closestIndex + 1;
            for (; j <= n; j++) {

                long result = calculateFibonacci(valueToResults, j + 1);

                if (result < n) {
                    if (result % 2 == 0) {
                        subtotal += result;
                    }

                } else if (result == n) {
                    if (result % 2 == 0) {
                        subtotal += result;
                        ArrayList<Long> list = new ArrayList<>();
                        list.add(j);
                        list.add(subtotal);
                        valueToPrimeSubtotals.put(result, list);
                        System.out.println(subtotal);
                        break;
                    }
                } else if (result > n) {
                    System.out.println(subtotal);
                    break;

                }

                ArrayList<Long> list = new ArrayList<>();
                list.add(j);
                list.add(subtotal);
                valueToPrimeSubtotals.put(result, list);
            }
        }
    }

}