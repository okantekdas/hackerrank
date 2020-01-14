package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EvenForest
 */
public class EvenForest {

    class Node {
        int value = 0;
        List<Integer> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

    }

    // Complete the evenForest function below.
    static int evenForest(HashMap<Integer, Node> valueToNode, int root) {

        if (valueToNode.get(root).children.size() == 0) {
            return 1;
        } else {
            Node current = valueToNode.get(root);
            int childCount = 0;
            if (root != 1) {
                childCount = 1;
            }

            for (int child : current.children) {
                childCount += evenForest(valueToNode, child);
            }
            if (childCount % 2 == 0) {
                removedEdgeCount++;
                childCount = 0;
            }
            return childCount;
        }
    }

    public static EvenForest evenForest = new EvenForest();

    public static int removedEdgeCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        HashMap<Integer, Node> valueToNode = new HashMap<>();

        for (int i = 0; i < tEdges; i++) {
            String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int tFromCurrent = Integer.parseInt(tFromTo[0]);
            int tToCurrent = Integer.parseInt(tFromTo[1]);

            Node parent = valueToNode.get(tToCurrent);
            if (parent == null) {
                parent = evenForest.new Node(tToCurrent);
                valueToNode.put(tToCurrent, parent);
            }

            Node child = valueToNode.get(tFromCurrent);
            if (child == null) {
                child = evenForest.new Node(tFromCurrent);
                valueToNode.put(tFromCurrent, child);
            }

            parent.children.add(child.value);
        }

        int res = evenForest(valueToNode, 1);

        System.out.println(removedEdgeCount);

        bufferedReader.close();
    }
}