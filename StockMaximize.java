package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Result {

    /*
     * Complete the 'stockmax' function below.
     *
     * The function is expected to return a LONG_INTEGER. The function accepts
     * INTEGER_ARRAY prices as parameter.
     */

    public static long stockmax(List<Integer> prices) {
        int max = 0;
        int profit = 0;
        for (int i = prices.size() - 1; i >= 0; i--) {
            if (prices.get(i) > max) {
                max = prices.get(i);
            }
            profit += max - prices.get(i);
        }

        return profit;
    }

}

/**
 * StockMaximize
 */
public class StockMaximize {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        StringBuilder strBuilder = new StringBuilder();

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            String[] pricesTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> prices = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int pricesItem = Integer.parseInt(pricesTemp[i]);
                prices.add(pricesItem);
            }

            long result = Result.stockmax(prices);

            strBuilder.append(String.valueOf(result));
            strBuilder.append("\n");
        }
        System.out.println(strBuilder.toString());

        bufferedReader.close();
    }

}