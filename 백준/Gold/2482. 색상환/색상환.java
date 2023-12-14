import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_003;


    static class Color{
        int able; int unable;

        public int getTotal(){
            return (this.able + this.unable) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if (N < K*2) {
            System.out.println(0);
            return;
        }

        Color[][] dp = new Color[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = new Color();
            }
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0].able = 1;
        }

        dp[1][1].unable = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K && j <= i/2; j++) {
                dp[i][j].able = (dp[i-1][j].getTotal())% MOD ;
                dp[i][j].unable = dp[i-1][j-1].able % MOD;
            }
        }

        System.out.println(dp[N][K].getTotal());

    }
}