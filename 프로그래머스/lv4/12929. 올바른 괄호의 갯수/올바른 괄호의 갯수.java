import java.util.Arrays;

class Solution {
    static int[] dp;
    static int[] pure;
    public int solution(int n) {
        int answer = 0;
        dp = new int[n+1];
        pure = new int[n+1];
        dp[1] = 1;
        pure[1] = 1;

        for (int i = 2; i <= n; i++) {
            func(i, n);
            pure[i] = dp[i-1];
            dp[i] += pure[i];
        }

        return dp[n];
    }
    static int func(int target, int n){

        if (dp[target] != 0) return dp[target];

        for (int i = 1; i < target; i++) {
            dp[target] += pure[i] * func(target-i, n);
        }
        return dp[target];
    }

}