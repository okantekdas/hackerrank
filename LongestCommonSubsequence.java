package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * LongestCommonSubsequence
 */
public class LongestCommonSubsequence {

    static LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();

    class LCS {
        int indexA;
        int indexB;

        public LCS(int indexA, int indexB) {
            this.indexA = indexA;
            this.indexB = indexB;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + indexA;
            result = prime * result + indexB;
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
            LCS other = (LCS) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (indexA != other.indexA)
                return false;
            if (indexB != other.indexB)
                return false;
            return true;
        }

        private LongestCommonSubsequence getEnclosingInstance() {
            return LongestCommonSubsequence.this;
        }

    }

    // Complete the longestCommonSubsequence function below.
    static List<Integer> longestCommonSubsequence(int[] a, int[] b, int indexA, int indexB,
            HashMap<LCS, List<Integer>> lcsToResult) {

        LCS lcs = longestCommonSubsequence.new LCS(indexA, indexB);

        if (lcsToResult.get(lcs) != null) {
            return lcsToResult.get(lcs);
        } else {
            List<Integer> result = new ArrayList<>();

            if (a.length == indexA || b.length == indexB) {
                lcsToResult.put(lcs, result);
                return result;
            }

            else if (a[indexA] == b[indexB]) {
                result.add(a[indexA]);
                result.addAll(longestCommonSubsequence(a, b, indexA + 1, indexB + 1, lcsToResult));
                lcsToResult.put(lcs, result);
                return result;
            } else {
                List<Integer> result1 = longestCommonSubsequence(a, b, indexA + 1, indexB, lcsToResult);
                List<Integer> result2 = longestCommonSubsequence(a, b, indexA, indexB + 1, lcsToResult);
                if (result1.size() > result2.size()) {
                    lcsToResult.put(lcs, result1);
                    return result1;
                } else {
                    lcsToResult.put(lcs, result2);
                    return result2;
                }

            }
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] b = new int[m];

        String[] bItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int bItem = Integer.parseInt(bItems[i]);
            b[i] = bItem;
        }

        HashMap<LCS, List<Integer>> lcsToResult = new HashMap();

        List<Integer> result = longestCommonSubsequence(a, b, 0, 0, lcsToResult);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            str.append(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                str.append(" ");
            }
        }

        str.append("\n");
        System.out.println(str.toString());
        scanner.close();
    }
}