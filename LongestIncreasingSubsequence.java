package hackerrank;

import java.util.Scanner;

/**
 * LongestIncreasingSubsequence
 */
public class LongestIncreasingSubsequence {

    static int maxResult = 1;
    static int max_ref = 1;

    static int _lis(int arr[], int n) {
        // base case
        if (n == 1)
            return 1;

        // 'max_ending_here' is length of LIS ending with arr[n-1]
        int res, max_ending_here = 1;

        /*
         * Recursively get all LIS ending with arr[0], arr[1] ... arr[n-2]. If arr[i-1]
         * is smaller than arr[n-1], and max ending with arr[n-1] needs to be updated,
         * then update it
         */
        for (int i = 1; i < n; i++) {
            res = _lis(arr, i);
            if (arr[i - 1] < arr[n - 1] && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }

        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }

    // Complete the longestIncreasingSubsequence function below.
    static int longestIncreasingSubsequence(int[] arr, int length) {

        if (length == 1) {
            return 1;
        }
        int biggest = 1;
        int result = 1;
        for (int i = 1; i < length; i++) {
            result = longestIncreasingSubsequence(arr, i);
            if (arr[i - 1] < arr[length - 1] && result + 1 > biggest) {
                biggest = result + 1;
            }

        }

        if (biggest > maxResult) {
            maxResult = biggest;
        }
        return biggest;

    }

    static int longestIncreasingSubsequence(int[] arr, int pivot, int current) {
        System.out.println("pivot: " + pivot + " current: " + current);

        if (current == arr.length || pivot == arr.length) {
            return 0;
        } else if (arr[pivot] < arr[current]) {
            return 1 + longestIncreasingSubsequence(arr, current, current + 1);
        } else {
            int result1 = 1 + longestIncreasingSubsequence(arr, pivot, current + 1);
            int result2 = 1 + longestIncreasingSubsequence(arr, pivot + 1, current + 1);

            return result2 > result1 ? result2 : result1;
        }

    }

    // Complete the longestIncreasingSubsequence function below.
    static int longestIncreasingSubsequence(int[] arr, int[] results) {

        int maxCount = 0;

        for (int j = 1; j < arr.length; j++) {
            for (int i = 0; i < j; i++) {
                if (arr[j] > arr[i]) {
                    int temp = results[i] + 1;
                    if (temp > results[j]) {
                        results[j] = temp;
                    }
                }
            }
            if (results[j] > maxCount) {
                maxCount = results[j];
            }

        }

        return maxCount + 1;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result0 = _lis(arr, arr.length);
        int result1 = longestIncreasingSubsequence(arr, arr.length);
        int result2 = longestIncreasingSubsequence(arr, 0, 0);
        int result3 = longestIncreasingSubsequence(arr, new int[arr.length]);

        System.out.println("Result0: " + String.valueOf(result0));
        System.out.println("Result1: " + String.valueOf(result1));
        System.out.println("Result2: " + String.valueOf(result2));
        System.out.println("Result3: " + String.valueOf(result3));

        scanner.close();
    }
}