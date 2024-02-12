import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int N;
    static int M;
    static int X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            arr[from][to] = dist;
        }

        int[] from = new int[N+1];
        Arrays.fill(from, INF);
        dijsktra(from, arr, true);

        int[] to = new int[N+1];
        Arrays.fill(to, INF);
        dijsktra(to, arr, false);


        int max = 0;
        for (int i = 1; i <= N; i++) {
//            if(from[i] == INF || to[i] == INF) continue;
            from[i] += to[i];
            max = Math.max(max, from[i]);
        }

        System.out.println(max);

    }

    private static void dijsktra(int[] visited, int[][] arr, boolean flag) {
        Queue<Integer> pk = new PriorityQueue<>();
        pk.add(X);
        visited[X] = 0;

        while(!pk.isEmpty()){
            int now = pk.poll();
            for (int i = 0; i <= N; i++) {
                int dist = flag? arr[now][i] : arr[i][now];
                if (dist > 0 && visited[now] + dist < visited[i]){
                    visited[i] = visited[now] + dist;
                    pk.add(i);
                }
            }
        }
    }

}