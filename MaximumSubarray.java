package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MaximumSubarray
 */
public class MaximumSubarray {

    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] arr) {
        int[] result = new int[2];

        int maxSubarraySum = 0;
        int maxSum = 0;
        int biggestNegative = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                maxSum += arr[i];
            } else if (arr[i] > biggestNegative) {
                biggestNegative = arr[i];

            }
            int localSum = arr[i];
            if (localSum > maxSubarraySum) {
                maxSubarraySum = localSum;
            }
            for (int j = i + 1; j < arr.length; j++) {
                localSum += arr[j];
                if (localSum > maxSubarraySum) {
                    maxSubarraySum = localSum;
                }
            }

        }
        result[0] = maxSubarraySum == 0 ? biggestNegative : maxSubarraySum;
        result[1] = maxSum > 0 ? maxSum : biggestNegative;
        return result;

    }

    // Complete the maxSubarray function below.
    static int[] maxSubarray2(int[] arr) {
        int[] result = new int[2];

        int maxSubarraySum = 0;
        int maxSum = 0;
        int biggestNegative = Integer.MIN_VALUE;

        if (arr[0] > 0) {
            maxSum += arr[0];
        } else if (arr[0] > biggestNegative) {
            biggestNegative = arr[0];

        }
        int localSum = arr[0];
        if (localSum > maxSubarraySum) {
            maxSubarraySum = localSum;
        }

        for (int j = 1; j < arr.length; j++) {
            if (arr[j] > 0) {
                maxSum += arr[j];
            } else if (arr[j] > biggestNegative) {
                biggestNegative = arr[j];

            }

            localSum += arr[j];
            if (arr[j] > localSum) {
                localSum = arr[j];
            }
            if (localSum > maxSubarraySum) {
                maxSubarraySum = localSum;
            }

        }

        result[0] = maxSubarraySum == 0 ? biggestNegative : maxSubarraySum;
        result[1] = maxSum > 0 ? maxSum : biggestNegative;
        return result;

    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // int t = scanner.nextInt();
        int t = Integer.parseInt(in.readLine());
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        StringBuilder str = new StringBuilder();

        for (int tItr = 0; tItr < t; tItr++) {
            // int n = scanner.nextInt();
            int n = Integer.parseInt(in.readLine());
            // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            // String[] arrItems = scanner.nextLine().split(" ");
            String[] arrItems = in.readLine().split(" ");
            // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            // int[] result = maxSubarray(arr);

            // for (int i = 0; i < result.length; i++) {
            // // bufferedWriter.write(String.valueOf(result[i]));
            // str.append(String.valueOf(result[i]));

            // if (i != result.length - 1) {
            // // bufferedWriter.write(" ");
            // str.append(" ");
            // }
            // }

            // // bufferedWriter.newLine();
            // str.append("\n");

            int[] result2 = maxSubarray2(arr);

            for (int i = 0; i < result2.length; i++) {
                // bufferedWriter.write(String.valueOf(result[i]));
                str.append(String.valueOf(result2[i]));

                if (i != result2.length - 1) {
                    // bufferedWriter.write(" ");
                    str.append(" ");
                }
            }

            // bufferedWriter.newLine();
            str.append("\n");
        }

        System.out.println(str.toString());

        // scanner.close();
    }
}