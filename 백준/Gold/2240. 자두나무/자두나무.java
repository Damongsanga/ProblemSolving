import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] tree = new int[T+1];
        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[W+1][T+1];

        int max = 0;
        for (int i = 0; i <= W; i++) {
            for (int j = 1; j <= T; j++) {
                if (i == 0){
                    if (tree[j] == 1) dp[i][j] = dp[i][j-1]+1;
                    else dp[i][j] = dp[i][j-1];
                }
                else {
                    if (tree[j]%2 != i%2) dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1])+1;
                    else dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]);
                }
            }
            max = Math.max(max, dp[i][T]);
        }
        
        System.out.println(max);
        
    }

}