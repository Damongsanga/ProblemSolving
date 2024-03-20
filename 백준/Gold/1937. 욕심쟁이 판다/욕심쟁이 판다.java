import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        int num; int r; int c;

        public Node(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Node node){
            return this.num - node.num;
        }

    }
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        Node[] nodes = new Node[n*n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                nodes[i*n + j] = new Node(arr[i][j], i, j);
            }
        }

        Arrays.sort(nodes);


        int max = 1;

        for (Node node : nodes){
            dp[node.r][node.c] = 1;
            for (int i = 0; i < 4; i++) {
                int nr = node.r + rr[i];
                int nc = node.c + rc[i];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (arr[nr][nc] >= arr[node.r][node.c]) continue;
                dp[node.r][node.c] = Math.max(dp[nr][nc]+1, dp[node.r][node.c]);
                max = Math.max(max, dp[node.r][node.c]);
            }
        }

        System.out.println(max);

    }
}
