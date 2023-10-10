import java.util.*;

class Solution {
    public int solution(int n) {
        int MOD = 1_000_000_007;

        // index :  1   2   3   4   5   6   7   8   9
        // pure  :  1   2   5   2   2   4   2   2   4
        // dp    :  1   3   10  21  58  156 ... ... ...
        // cache :  0   0   0   0   2   8   30  ... ...

        // 더 작은 문제로 쪼갤 수 있다.
        // 각 i*3 개에 대한 쪼개지지 않는 경우의 수는 위의 pure 수이고, 그림 예시는 아래와 링크 참고
        // https://school.programmers.co.kr/questions/47346
        // 보면 3 단위로 반복되는 것을 알 수 있음으로 이를 모두 더하지 말고 크기 3짜리 배열 (cache) 에 값을 저장
        // 전까지의 누적 합을 저장해놓으면 O(N)만에 풀이 가능

        switch (n) {
            case(1) : return 1;
            case(2) : return 3;
            case(3) : return 10;
        }
        long[] dp = new long[n+1];
        dp[1] = 1; dp[2] = 3; dp[3] = 10;
        long[] cache = new long[] {8,0,2}; // index 1 부터 시작
        int[] pattern = new int[] {4,2,2}; // index 1 부터 시작

        for (int i = 4; i <= n; i++) {
            int r = i%3;
            dp[i] = dp[i-1] + 2 * dp[i-2] + 5 * dp[i-3] + pattern[r];
            dp[i] += cache[r];
            dp[i] %= MOD;

            cache[r] += 2 * dp[i-1] + 2 * dp[i-2] + 4 * dp[i-3]; // pattern을 고려하지 않아도 된다..!
            cache[r] %= MOD;
        }

        return (int) dp[n];
    }
}