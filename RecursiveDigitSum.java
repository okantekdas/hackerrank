package hackerrank;

/**
 * RecursiveDigitSum
 */
public class RecursiveDigitSum {

    static int superDigit(String n, int k) {
        long tempNumber = 0;
        for (int i = 0; i < n.length(); i++) {
            String a = String.valueOf(n.charAt(i));
            tempNumber += Integer.valueOf(a);
        }

        tempNumber *= k;

        return (int)supDigit(tempNumber);
    }

    static long supDigit(long n) {

        if (n < 10) {
            return n;
        } else {
            int tempNumber = 0;
            while (n > 0) {
                int remainder =(int) n % 10;
                n /= 10;
                tempNumber += remainder;
            }
            return supDigit(tempNumber);
        }
    }

    static int supDigit(String n) {

        if (n.length() == 1) {
            return Integer.valueOf(n);
        } else {
            int tempNumber = 0;
            for (int i = 0; i < n.length(); i++) {
                String a = String.valueOf(n.charAt(i));
                tempNumber += Integer.valueOf(a);
            }
            return supDigit(String.valueOf(tempNumber));
        }
    }

    public static void main(String[] args) {

        int result = superDigit("9875", 4);

        System.out.println("Result: " + result);

    }
}