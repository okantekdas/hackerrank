package hackerrank;

import java.io.IOException;
import java.util.Scanner;

/**
 * BreadthFirstSearch
 */
public class BreadthFirstSearch {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {

        int[] result = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            result[i] = -1;

        }

        // Arrays.sort(edges, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            int tempTo = to >= s ? to - 2 : to - 1;
            int tempFrom = from >= s ? from - 2 : from - 1;

            if (from == s) {
                result[tempTo] = 6;
            } else if (to == s) {
                result[tempFrom] = 6;
            }
        }

        // System.out.println(Arrays.toString(result));

        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            int tempTo = to >= s ? to - 2 : to - 1;
            int tempFrom = from >= s ? from - 2 : from - 1;

            if (from != s && to != s) {
                if (result[tempFrom] == result[tempTo]) {
                    // do nothing
                } else {
                    if (result[tempFrom] >= 6 && result[tempTo] >= 6) {

                        if (result[tempFrom] > result[tempTo]) {
                            result[tempFrom] = result[tempTo] + 6;

                        } else {
                            result[tempTo] = result[tempFrom] + 6;
                        }
                    } else {
                        if (result[tempFrom] > result[tempTo]) {
                            result[tempTo] = result[tempFrom] + 6;

                        } else {
                            result[tempFrom] = result[tempTo] + 6;
                        }
                    }

                }

            }

        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new
        // FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        StringBuilder stringBuilder = new StringBuilder();

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                stringBuilder.append(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    stringBuilder.append(" ");
                }
            }

            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());

        scanner.close();
    }

}