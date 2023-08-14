class Solution {
    public int solution(int n) {
        //long으로 구현해야한다.
        long[] dp = new long[n + 1];
        // 초기값 세팅
        dp[0] = 1;
        dp[2] = 3;
        
        // 2개 단위로 깔끔하게 떨어지지 않는 경우를 더하는 값
        long adder = 0;
        
        // f(n) = f(n-2) * 3 + (f(n-4) + f(n-6) + ... + f(2) + f(0)) * 2 
        for (int i = 4; i <= n; i += 2) {
            adder = (adder + dp[i - 4]) % 1_000_000_007; // 
            dp[i] = (dp[i - 2] * 3 + 2 * adder) % 1_000_000_007;
        }

        return (int) dp[n] % 1_000_000_007 ;
    }

}