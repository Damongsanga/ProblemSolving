import java.util.Arrays;
import java.util.Collections;

class Solution {
    static int aMax;
    static int cMax;
    public int solution(int alp, int cop, int[][] problems) {
        final int INF = 987654321;

        aMax = 0;
        cMax = 0;

        for (int i = 0; i < problems.length; i++) {
            aMax = Math.max(problems[i][0], aMax);
            cMax = Math.max(problems[i][1], cMax);
        }
        alp = Math.min(alp, aMax);
        cop = Math.min(cop, cMax);

        int[][] dp = new int[aMax+1][cMax+1];


        for (int i = alp; i <= aMax; i++) {
            for (int j = cop; j <= cMax; j++) {
                if (i == alp && j == cop) {
                    dp[i][j] = 0; continue;
                }
                dp[i][j] = INF;
                dp[i][j] = Math.min(dp[i][j], Math.max(i>0? dp[i-1][j] : 0, j > 0? dp[i][j-1] : 0)+1);
            }
        }
        Arrays.sort(problems, (o1, o2) -> o1[0] - o2[0]);
        dp2D(dp, problems, alp, cop, 0);
        Arrays.sort(problems, (o1, o2) -> o1[1] - o2[1]);
        dp2D(dp, problems, alp, cop, 1);

        return dp[aMax][cMax];
    }

    static void dp2D(int[][] dp, int[][] problems, int alp, int cop, int x){
        for (int[] problem : problems) {
            int aSrt = problem[0];
            int cSrt = problem[1];
            int a = problem[2];
            int c= problem[3];
            int cost = problem[4];
            if (x == 0){
                for (int i = Math.max(alp, aSrt); i <= aMax; i++) {
                    for (int j = Math.max(cop, cSrt); j <= cMax; j++) {
                        dp[Math.min(aMax, i+a)][Math.min(cMax, j+c)] = Math.min(dp[i][j] + cost, dp[Math.min(aMax, i+a)][Math.min(cMax, j+c)]);
                    }
                }
            } else {
                for (int i = Math.max(cop, cSrt); i <= cMax; i++) {
                    for (int j = Math.max(alp, aSrt); j <= aMax; j++) {
                        dp[Math.min(aMax, j+a)][Math.min(cMax, i+c)] = Math.min(dp[j][i] + cost, dp[Math.min(aMax, j+a)][Math.min(cMax, i+c)]);
                    }
                }
            }

        }
    }

}