package hackerrank;

import java.util.Scanner;

/**
 * HexagonalGrid
 */
public class HexagonalGrid {

    /*
     * Complete the hexagonalGrid function below.
     */
    static boolean hexagonalGrid(int[][] grid, int pointer) {

        if (pointer == grid[0].length) {
            return true;
        } else {
            if (grid[0][pointer] == 1 && grid[1][pointer] == 1) {
                return hexagonalGrid(grid, pointer + 1);
            } else if (grid[0][pointer] == 0 && grid[1][pointer] == 0) {
                grid[0][pointer] = 1;
                grid[1][pointer] = 1;
                return hexagonalGrid(grid, pointer + 1);

            } else if (grid[0][pointer] == 0 && grid[1][pointer] == 1) {
                if (pointer + 1 < grid[0].length && grid[0][pointer + 1] == 0) {
                    grid[0][pointer] = 1;
                    grid[0][pointer + 1] = 1;
                    return hexagonalGrid(grid, pointer + 1);
                }
            } else if (grid[0][pointer] == 1 && grid[1][pointer] == 0) {
                boolean result1 = false;
                boolean result2 = false;
                boolean isEnteredFirstCondition = false;
                if (pointer + 1 < grid[0].length && grid[1][pointer + 1] == 0) {
                    isEnteredFirstCondition = true;
                    grid[1][pointer] = 1;
                    grid[1][pointer + 1] = 1;
                    result1 = hexagonalGrid(grid, pointer + 1);
                }
                if (pointer + 1 < grid[0].length && grid[0][pointer + 1] == 0) {
                    if (isEnteredFirstCondition) {
                        // revert changes
                        grid[1][pointer + 1] = 0;
                    }
                    // apply solution
                    grid[0][pointer + 1] = 1;
                    grid[1][pointer] = 1;
                    result2 = hexagonalGrid(grid, pointer + 1);
                }

                return result1 | result2;
            } else {
                return false;
            }
        }
        return false;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int t = Integer.parseInt(scanner.nextLine().trim());

        int testCounter = 1;

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            String a = scanner.nextLine();

            String b = scanner.nextLine();

            int[][] grid = new int[2][n];

            int count = 0;
            int count1 = 0;
            for (char c : a.toCharArray()) {
                if (c == '0') {
                    grid[0][count++] = 0;
                } else {
                    grid[0][count++] = 1;
                    count1++;
                }
            }
            count = 0;
            for (char c : b.toCharArray()) {
                if (c == '0') {
                    grid[1][count++] = 0;
                } else {
                    grid[1][count++] = 1;
                    count1++;
                }
            }

            boolean result = false;
            if (count1 % 2 == 0) {
                result = hexagonalGrid(grid, 0);
            }

            System.out.println(result == true ? "YES" : "NO");
            testCounter++;
        }

    }
}