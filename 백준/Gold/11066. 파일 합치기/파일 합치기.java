import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int INF = 987654321;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+1];
            int[] prefixSum = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = (prefixSum[i-1] + arr[i]);
            }

            int[][] dp = new int[N+1][N+1];

            for (int gap = 1; gap < N; gap++) {
                for (int srt = 1; srt+gap <= N; srt++) {
                    int end = srt+gap;
                    dp[srt][end] = INF;
                    for (int mid = srt; mid < end; mid++) {
                        dp[srt][end] = Math.min(dp[srt][mid] + dp[mid+1][end] + prefixSum[end] - prefixSum[srt-1], dp[srt][end]);
                    }
                }
            }

            sb.append(dp[1][N] + "\n");
        }

        System.out.println(sb.toString().trim());

    }
}