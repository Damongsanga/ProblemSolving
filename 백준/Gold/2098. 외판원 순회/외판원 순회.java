import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int num;
        int r;
        int c;

        public Node(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Node node) {
            return this.num - node.num;
        }

    }

    static int[] rr = {-1, 0, 1, 0};
    static int[] rc = {0, 1, 0, -1};

    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][][] dp = new int[n][n][1 << n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int bit = 0; bit < 1 << n; bit++) {
                    if (Integer.bitCount(bit) == 1) continue;
                    dp[i][j][bit] = INF;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int cnt = 2; cnt <= n; cnt++) { // 거쳐갈 도시 갯수
            for (int bit = 0; bit < 1 << n; bit++) { // bit
                if (Integer.bitCount(bit) != cnt) continue; // 경로 갯수 안맞으면 제외

                for (int srt = 0; srt < n; srt++) { // 현재 위치
                    for (int end = 0; end < n; end++) { // 마지막 위치
                        if (srt == end) continue;

                        if ((bit & (1 << srt)) != 1 << srt) continue; // 경로에 시작점 포함 안되어있으면 제외
                        if ((bit & (1 << end)) != 1 << end) continue; // 경로에 마지막 위치 안되어있으면 제외

                        for (int j = 0; j < n; j++) { // 직전 위치
                            if (arr[j][end] == 0) continue; // 직전경로에서 마지막 위치로 갈 경로가 없음
                            if ((bit & (1 << j)) != 1 << j) continue; // 직전위치가 경로에 없으면 제외
                            dp[srt][end][bit] = Math.min(dp[srt][j][bit - (1 << end)] + arr[j][end], dp[srt][end][bit]);
                        }

                    }
                }
            }
        }
        

        int answer = INF;

        for (int i = 0; i < n; i++) { // 시작시점
            for (int j = 0; j < n; j++) { // 마지막 지점 (시작지점으로 돌아오기 직전 지점)
                if (i == j) continue;
                if (arr[j][i] == 0)continue;
                answer = Math.min(answer, dp[i][j][(1 << n) - 1] + arr[j][i]);
            }
        }

        System.out.println(answer);

    }
}
