import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
    	int[] dp = new int[y+1];
    	
    	for (int i = 0; i <= y; i++) {
			dp[i] = 987654321;
		}
    	
    	dp[x] = 0;
    	
    	for (int i = 1; i <= y; i++) {
			if (i >= n && dp[i-n] != 987654321) dp[i] = Math.min(dp[i], dp[i-n] + 1);
			if (i % 2 == 0 && dp[i/2] != 987654321) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			if (i % 3 == 0 && dp[i/3] != 987654321) dp[i] = Math.min(dp[i], dp[i/3] + 1);
		}
    	
        return dp[y] == 987654321 ? -1 : dp[y];
    }
}