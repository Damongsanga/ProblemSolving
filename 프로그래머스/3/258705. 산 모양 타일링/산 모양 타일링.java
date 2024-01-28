import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        if (tops[0] == 1){
            dp[0][0] = 1;
            dp[0][1] = 3;
        } else {
            dp[0][0] = 1;
            dp[0][1] = 2;
        }

        final int MOD = 10007;

        for (int i = 1; i < n; i++) {
            if (tops[i] == 1){
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
                dp[i][1] = (dp[i-1][0] * 2 + dp[i-1][1] * 3) % MOD;
            } else {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
                dp[i][1] = (dp[i-1][0] + dp[i-1][1] * 2) % MOD;
            }
        }

        return (dp[n-1][0] + dp[n-1][1]) % MOD;

    }
}