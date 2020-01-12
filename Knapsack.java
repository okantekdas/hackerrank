package hackerrank;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Knapsack
 */
public class Knapsack {

    // Complete the unboundedKnapsack function below.
    static int unboundedKnapsack(int k, HashSet<Integer> variables, int subtotal, int currentVariable) {
        if (subtotal > k) {
            return subtotal - currentVariable;
        } else if (subtotal == k) {
            return subtotal;
        }
        int biggest = 0;
        for (int a : variables) {
            int result = unboundedKnapsack(k, variables, subtotal + a, a);
            if (result == k) {
                return k;
            } else if (result > biggest) {
                biggest = result;
            }

        }
        return biggest;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        StringBuilder str = new StringBuilder();

        for (int j = 0; j < t; j++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] arr = new int[n];

            HashSet<Integer> variables = new HashSet<>();

            String[] arrItems = scanner.nextLine().split(" ");
            // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                variables.add(arrItem);
            }
            int result = unboundedKnapsack(k, variables, 0, 0);
            str.append(result);
            str.append("\n");

        }

        System.out.println(str.toString());

        scanner.close();
    }
}