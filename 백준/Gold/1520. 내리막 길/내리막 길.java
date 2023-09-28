import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int[] rr = {1,0,-1,0};
    static int[] rc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(DFS(0,0,dp,arr,N,M));

    }

    static int DFS(int r, int c, int[][] dp, int[][] arr, int N, int M) {

        // 탈출조건 : 도착했다면 가능한 경우의 수가 1개 는 것임으로 1 리턴
        if (r == N - 1 && c == M - 1) {
            return 1;
        }

        // -1 이 아니라면 방문했다는 뜻임으로 dp 값 그대로 리턴
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        // 방문하지 않았으면 방문처리
        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + rr[i];
            int nc = c + rc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            if (arr[r][c] > arr[nr][nc]) dp[r][c] += DFS(nr, nc, dp, arr, N, M);

        }
        // 마지막에는 재귀가 모두 끝나고 시작점 (r = 0, c = 0) 만 남음
        return dp[r][c];
    }
}