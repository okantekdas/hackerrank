package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Floyd {

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

    static int findShortestPath(int from, int to, int[][] fromToWeight, boolean[] visited, int[][] shortestFromToPath) {

        if (from == to) {
            return 0;
        } else if (visited[from]) {
            return -1;
        } else if (shortestFromToPath[from][to] != Integer.MAX_VALUE) {
            return shortestFromToPath[from][to];
        } else {
            int shortestPath = Integer.MAX_VALUE;
            int subtotal = 0;

            visited = Arrays.copyOf(visited, fromToWeight.length);
            visited[from] = true;
            for (int i = 0; i < fromToWeight[from].length; i++) {
                if (fromToWeight[from][i] != Integer.MAX_VALUE && fromToWeight[from][i] != 0) {
                    subtotal = findShortestPath(i, to, fromToWeight, visited, shortestFromToPath);
                    if (subtotal != -1) {
                        subtotal += fromToWeight[from][i];
                        if (subtotal < shortestPath) {
                            shortestPath = subtotal;
                        }
                    }
                }
            }

            if (shortestPath != Integer.MAX_VALUE) {
                shortestFromToPath[from][to] = shortestPath;
            }

            return shortestPath == Integer.MAX_VALUE ? -1 : shortestPath;
        }

    }


    public static void main(String[] args) {
        FastReader in = new FastReader();


        String[] roadNodesEdges = in.nextLine().split(" ");
        int roadNodes = Integer.parseInt(roadNodesEdges[0].trim());
        int roadEdges = Integer.parseInt(roadNodesEdges[1].trim());

        int[] roadFrom = new int[roadEdges];
        int[] roadTo = new int[roadEdges];
        int[] roadWeight = new int[roadEdges];

        int[][] fromToWeight = new int[roadNodes][roadNodes];
        int[][] shortestFromToPath = new int[roadNodes][roadNodes];

        for (int i = 0; i < roadNodes; i++) {
            Arrays.fill(fromToWeight[i], Integer.MAX_VALUE);
            fromToWeight[i][i] = 0;
        }
        for (int i = 0; i < roadNodes; i++) {
            Arrays.fill(shortestFromToPath[i], Integer.MAX_VALUE);
            shortestFromToPath[i][i] = 0;
        }

        for (int i = 0; i < roadEdges; i++) {
            String[] roadFromToWeight = in.nextLine().split(" ");
            roadFrom[i] = Integer.parseInt(roadFromToWeight[0].trim());
            roadTo[i] = Integer.parseInt(roadFromToWeight[1].trim());
            roadWeight[i] = Integer.parseInt(roadFromToWeight[2].trim());

            fromToWeight[roadFrom[i] - 1][roadTo[i] - 1] = roadWeight[i];
        }

        int q = in.nextInt();
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        StringBuilder stringBuilder = new StringBuilder();
        for (int qItr = 0; qItr < q; qItr++) {
            String[] xy = in.nextLine().split(" ");

            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if (x == y) {
                stringBuilder.append("0");
                stringBuilder.append("\n");
            } else {
                boolean[] visited = new boolean[roadNodes];
                int result = findShortestPath(x - 1, y - 1, fromToWeight, visited, shortestFromToPath);
                // System.out.println("Result: " + result);
                stringBuilder.append(result);
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder.toString());

    }
}