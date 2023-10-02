import java.util.Arrays;

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];

        Arrays.sort(money);


        for (int i = 0; i <money.length; i++) {
            int coin = money[i];
            for (int j = 1; j <= n; j++) {
                if (j < coin) continue;
                dp[j] += dp[j-coin];
                if (j==coin) dp[j] += 1;
                if (dp[j] >= 1_000_000_007) dp[j] %= 1_000_000_007;
            }
            
        }

        return dp[n];
    }
}