package hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * FibonacciModified
 */
public class FibonacciModified {

    // Complete the fibonacciModified function below.
    static BigInteger fibonacciModified(int t1, int t2, BigInteger[] results, int n) {
        if (n == 1) {
            results[n - 1] = BigInteger.valueOf(t1);
            return BigInteger.valueOf(t1);
        } else if (n == 2) {
            results[n - 1] = BigInteger.valueOf(t2);
            return BigInteger.valueOf(t2);
        } else if (results[n - 1] != null) {
            return results[n - 1];
        } else {
            BigInteger value1 = fibonacciModified(t1, t2, results, n - 2);
            results[n - 3] = value1;
            BigInteger value2 = fibonacciModified(t1, t2, results, n - 1);
            results[n - 2] = value2;
            return value1.add(value2.multiply(value2));

        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] t1T2n = scanner.nextLine().split(" ");

        int t1 = Integer.parseInt(t1T2n[0]);

        int t2 = Integer.parseInt(t1T2n[1]);

        int n = Integer.parseInt(t1T2n[2]);

        final BigInteger[] results = new BigInteger[n];

        BigInteger result = fibonacciModified(t1, t2, results, n);

        System.out.println(result);

        scanner.close();
    }
}