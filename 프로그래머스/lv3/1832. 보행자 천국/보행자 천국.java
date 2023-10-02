import java.util.Arrays;

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = dfs(0,0,dp,m,n,cityMap,MOD);

//        System.out.println(Arrays.deepToString(dp));

        return answer;

    }
    static int dfs(int r, int c, int[][] dp, int m, int n, int[][] cityMap, int MOD){

        if (r == m-1 && c == n-1) return 1;
        if(dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;
        int idx_x = 1;
        while(r + idx_x < m && cityMap[r+idx_x][c] == 2){
            idx_x++;
        }
        int idx_y = 1;
        while(c + idx_y < n && cityMap[r][c+idx_y] == 2){
            idx_y++;
        }

        if (r + idx_x < m && cityMap[r+idx_x][c] != 1) dp[r][c] += dfs(r+idx_x, c, dp, m,n, cityMap, MOD)%MOD;
        if (c + idx_y < n && cityMap[r][c+idx_y] != 1) dp[r][c] += dfs(r, c+idx_y, dp, m,n, cityMap, MOD)%MOD;
        return dp[r][c] % MOD;

    }
}