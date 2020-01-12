package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Dijkstra
 */
public class Dijkstra {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // Complete the shortestReach function below.
    static int[] shortestReach(int n, int s, int[][] edgesMatrix) {

        boolean[] visited = new boolean[n];
        visited[s] = true;
        int current = s;
        do {

            for (int i = 0; i < n; i++) {
                if (edgesMatrix[current][i] + edgesMatrix[s][current] < edgesMatrix[s][i]) {
                    edgesMatrix[s][i] = edgesMatrix[current][i] + edgesMatrix[s][current];
                }
            }
            int smallest = Integer.MAX_VALUE / 2;
            int smallestIndex = -1;
            for (int j = 0; j < n; j++) {
                if (j != s && !visited[j] && edgesMatrix[s][j] <= smallest) {
                    smallest = edgesMatrix[s][j];
                    smallestIndex = j;
                }
            }
            visited[smallestIndex] = true;
            current = smallestIndex;

        } while (!isVisitedAll(visited));

        return edgesMatrix[s];

    }

    static boolean isVisitedAll(boolean visited[]) {
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }
        return true;
    }

    // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        FastReader in = new FastReader();

        int t = in.nextInt();
        // int t = scanner.nextInt();
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = in.nextLine().split(" ");
            // String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][3];

            int[][] edgeMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(edgeMatrix[i], Integer.MAX_VALUE / 2);
                edgeMatrix[i][i] = 0;
            }

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = in.nextLine().split(" ");
                // String[] edgesRowItems = scanner.nextLine().split(" ");
                // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
                int from = edges[i][0];
                int to = edges[i][1];
                int weight = edges[i][2];
                if (edgeMatrix[from - 1][to - 1] != 0 && weight < edgeMatrix[from - 1][to - 1]) {
                    edgeMatrix[from - 1][to - 1] = weight;
                    edgeMatrix[to - 1][from - 1] = weight;
                }

            }

            int s = in.nextInt();
            // int s = scanner.nextInt();
            // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, s - 1, edgeMatrix);

            for (int i = 0; i < result.length; i++) {
                if (i != s - 1) {
                    if (result[i] == Integer.MAX_VALUE / 2) {
                        result[i] = -1;
                    }
                    System.out.print(result[i]);

                    if (i != result.length - 1) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }

        // scanner.close();
    }
}