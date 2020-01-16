package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Kruskal
 */
class Edge {

    public int from;
    public int to;
    public int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + from;
        result = prime * result + to;
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
        Edge other = (Edge) obj;
        if (from != other.from)
            return false;
        if (to != other.to)
            return false;
        return true;
    }

    public static int kruskals(ArrayList<Edge> edges, int[] parenthood) {

        int totalWeight = 0;

        for (Edge edge : edges) {

            int from = edge.from - 1;
            int to = edge.to - 1;
            int fromParent = find(from, parenthood);
            int toParent = find(to, parenthood);
            if (fromParent != toParent) {
                System.out.println(edge);
                union(fromParent, toParent, parenthood);
                totalWeight += edge.weight;
            }

        }

        return totalWeight;

    }

    public static int find(int node, int[] parenthood) {
        if (parenthood[node] < 0) {
            return node;
        } else {
            return find(parenthood[node], parenthood);
        }
    }

    public static void union(int fromParent, int toParent, int parenthood[]) {
        if (parenthood[fromParent] == -1 || parenthood[toParent] == -1) {
            parenthood[fromParent]--;
            parenthood[toParent] = fromParent;
        } else {
            if (parenthood[fromParent] > parenthood[toParent]) {
                parenthood[toParent] += parenthood[fromParent];
                parenthood[fromParent] = toParent;
            } else {
                parenthood[fromParent] += parenthood[toParent];
                parenthood[toParent] = fromParent;
            }
        }

    }

    @Override
    public String toString() {
        return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
    }
}

public class Kruskal {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        ArrayList<Edge> edges = new ArrayList<>();

        int[] parenthood = new int[gNodes];
        Arrays.fill(parenthood, -1);

        for (int i = 0; i < gEdges; i++) {
            String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            gFrom.add(Integer.parseInt(gFromToWeight[0]));
            gTo.add(Integer.parseInt(gFromToWeight[1]));
            gWeight.add(Integer.parseInt(gFromToWeight[2]));

            Edge edge = new Edge(Integer.parseInt(gFromToWeight[0]), Integer.parseInt(gFromToWeight[1]),
                    Integer.parseInt(gFromToWeight[2]));
            edges.add(edge);
        }

        edges.sort(new Comparator<Edge>() {

            @Override
            public int compare(Edge e1, Edge e2) {

                return e1.weight > e2.weight ? 1 : -1;
            }

        });
        int res = Edge.kruskals(edges, parenthood);

        System.out.println(res);

        bufferedReader.close();
    }

}