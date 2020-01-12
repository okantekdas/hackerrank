package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * KFactorization
 */
public class KFactorization {

    // Complete the kFactorization function below.
    static int[] kFactorization(int n, int[] A) {

        Arrays.sort(A);
        List<Integer> states = kFactor(n, A, A.length - 1, new ArrayList<>());
        // System.out.println(Arrays.toString(states.toArray()));

        int[] result = new int[states.size()];
        int y=0;
        for (int i = result.length-1; i >-1; i--) {
            result[y] = states.get(i);
            y++;
        }

        return result;

    }

    static List<Integer> kFactor(int n, int[] A, int index, List<Integer> states) {
        if (index < 0 || n < 1) {
            states = new ArrayList<Integer>();
            states.add(-1);
            return states;
        } else if (n == 1) {
            states.add(1);
            return states;
        } else {
            int current = A[index];
            System.out.println("Current: " + current);

            if (n % current == 0) {
                states.add(n);
                return kFactor(n / current, A, index, states);
            } else {
                return kFactor(n, A, --index, states);
            }
        }

    }

    public static void main(String[] args) {

        // int n = 72;
        // int[] A = new int[] { 2, 4, 6, 9, 3, 7, 16, 10, 5 };
        // int n = 15;
        // int[] A = new int[] { 2, 10, 6, 9, 11 };
        // int n = 12;
        // int[] A = new int[] { 2, 3, 4 };
        int n = 175840877;
        int[] A = new int[] { 4, 5, 6, 7, 8, 10, 12, 17, 18, 19 };

        int[] result = kFactorization(n, A);

        System.out.println("Result: " + Arrays.toString(result));

    }
}