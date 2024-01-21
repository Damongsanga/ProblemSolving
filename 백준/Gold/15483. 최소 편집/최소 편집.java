import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] from = br.readLine().toCharArray();
        char[] to = br.readLine().toCharArray();
        int[][] dp = new int[from.length+1][to.length+1];

        for (int i = 0; i <= from.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= to.length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= from.length; i++) {
            for (int j = 1; j <= to.length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                dp[i][j] = Math.min(dp[i][j], from[i-1] == to[j-1] ? dp[i-1][j-1] : dp[i-1][j-1]+1);
            }
        }

        System.out.println(dp[from.length][to.length]);

    }

}