package hackerrank;

/**
 * Test
 */
public class PowerSum {

    static int powerSum(int X, int N) {

        return powSum(X, N, 1);

    }

    static int powSum(int X, int N, int number) {
        if (Math.pow(number, N) == X) {
            return 1;
        } else if (Math.pow(number, N) > X) {
            return 0;
        } else {
            return powSum(X, N, number + 1) + powSum(X - (int) Math.pow(number, N), N, number + 1);
        }

    }

    public static void main(String[] args) {
        int X = 100;
        int N = 3;
        int result = powerSum(X, N);

        System.out.println(result);
    }
}