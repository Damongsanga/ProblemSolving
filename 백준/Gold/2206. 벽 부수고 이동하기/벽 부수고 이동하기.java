import java.io.*;
import java.util.*;


public class Main {
    static class Node {
        int r; int c; int dist; int wall;

        public Node(int r, int c, int dist, int wall) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.wall = wall;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    ", wall=" + wall +
                    '}';
        }
    }

    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp.charAt(j)- '0';
            }
        }

        int[][][] visited = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j][0] = Integer.MAX_VALUE;
                visited[i][j][1] = Integer.MAX_VALUE;
            }
        }

        int answer = -1;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0,0,1,0));
        visited[0][0][0] = 1;
        while(!queue.isEmpty()){
            Node now = queue.poll();

            if (now.r == N-1 && now.c == M-1){
                answer = now.dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int r = now.r + rr[i];
                int c = now.c + rc[i];
                int wall = now.wall;
                if (r < 0 || c < 0 || r >= N || c >= M) continue;

                if (arr[r][c] == 1 && wall == 0) wall = 1; // 벽 안부셨으면 벽 부수기
                else if (arr[r][c] == 1 && wall == 1) continue; // 벽 부셨으면 못지나감
                if (visited[r][c][wall] > now.dist+1){
                    visited[r][c][wall] = now.dist+1;
                    queue.add(new Node(r,c, now.dist+1, wall));
                }
            }
        }

        System.out.println(answer);

    }

}