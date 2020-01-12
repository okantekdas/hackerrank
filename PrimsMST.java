package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * PrimsMST
 */
public class PrimsMST {

    class Neighbor {
        int from;
        int to;
        int weight;

        Neighbor(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            String str = "(from: " + from + " to: " + to + " weight: " + weight + ")";
            return str;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + from;
            result = prime * result + to;
            result = prime * result + weight;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Neighbor other = (Neighbor) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (from != other.from)
                return false;
            if (to != other.to)
                return false;
            if (weight != other.weight)
                return false;
            return true;
        }

        private PrimsMST getEnclosingInstance() {
            return PrimsMST.this;
        }

    }

    // Complete the prims function below.
    static int prims(int n, int[][] edges, int start) {

        boolean[] visited = new boolean[n];
        visited[start - 1] = true;

        HashMap<Integer, HashSet<Neighbor>> nodeToNeighbour = new HashMap<Integer, HashSet<Neighbor>>();

        for (int i = 0; i < n; i++) {
            nodeToNeighbour.put(i + 1, new HashSet<Neighbor>());
        }
        PrimsMST primMST = new PrimsMST();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            Neighbor neighbor = primMST.new Neighbor(from, to, weight);
            nodeToNeighbour.get(from).add(neighbor);

            Neighbor neighbor2 = primMST.new Neighbor(to, from, weight);
            nodeToNeighbour.get(to).add(neighbor2);

        }

        int unvisitedCount = n - 1;
        int totalWeight = 0;
        HashSet<Neighbor> unvisitedNeighbors = new HashSet<>();

        while (unvisitedCount > 0) {
            int from = start;
            HashSet<Neighbor> neighbors = nodeToNeighbour.get(from);
            unvisitedNeighbors.addAll(neighbors);
            int shortest = Integer.MAX_VALUE;
            int shortestTo = 0;
            Neighbor smallestNeighbor = null;

            System.out.println("unvisitedCount: " + unvisitedCount);
            System.out.println("Options: " + Arrays.toString(unvisitedNeighbors.toArray()));
            for (Neighbor neighbor : unvisitedNeighbors) {
                int to = neighbor.to;
                int weight = neighbor.weight;
                if (!visited[to - 1] && weight < shortest) {
                    shortest = weight;
                    shortestTo = to;
                    smallestNeighbor = neighbor;
                }
            }

            visited[shortestTo - 1] = true;
            System.out.println("Smallest neighbor picked: " + smallestNeighbor);
            Neighbor reversedSmallestNeighbor = primMST.new Neighbor(smallestNeighbor.to, smallestNeighbor.from,
                    smallestNeighbor.weight);

            unvisitedNeighbors.remove(smallestNeighbor);
            nodeToNeighbour.get(smallestNeighbor.from).remove(smallestNeighbor);
            nodeToNeighbour.get(smallestNeighbor.to).remove(reversedSmallestNeighbor);

            unvisitedCount--;
            totalWeight += shortest;
            start = shortestTo;
        }

        return totalWeight;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        stringBuilder.append(String.valueOf(result));
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());

        scanner.close();
    }
}