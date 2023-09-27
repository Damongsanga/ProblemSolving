import java.util.Arrays;

class Solution {
    public int solution(int[] money) {
        int N = money.length;
        int[] dp1 = new int[N]; // 첫 집을 터는 경우
        int[] dp2 = new int[N]; // 첫 집을 안털고 둘째 집을 터는 경우 
        
        dp1[0] = money[0];
        dp1[1] = money[0];
        dp2[0] = 0;
        dp2[1] = money[1];
        
        for (int i = 2; i < N-1; i++) {
			dp1[i] = Math.max(dp1[i-2] + money[i], dp1[i-1]);
			dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
		}
        dp1[N-1] = dp1[N-2];
        dp2[N-1] = Math.max(dp2[N-3] + money[N-1], dp2[N-2]);
        
        
        return Math.max(dp1[N-1], dp2[N-1]);

    }
    
}