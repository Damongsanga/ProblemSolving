import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int N;
    static int M;
    static int X;

    static class Edge implements Comparable<Edge> {
        int from; int to; int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

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
            from[i] += to[i];
            max = Math.max(max, from[i]);
        }

        System.out.println(max);

    }

    private static void dijsktra(int[] dist, int[][] arr, boolean flag) {
        Queue<Edge> pk = new PriorityQueue<>();
        boolean[] visited =new boolean[N+1];

        pk.add(new Edge(X, X, 0));
        dist[X] = 0;

        while(!pk.isEmpty()){
            Edge e = pk.poll();
            int now = e.to;
            visited[now] = true;

            for (int i = 0; i <= N; i++) {
                int d = flag? arr[now][i] : arr[i][now];
                if (visited[i]) continue;
                if (d > 0 && dist[now] + d < dist[i]){
                    dist[i] = dist[now] + d;
                    pk.add(new Edge(now, i, dist[i]));
                }
            }
        }
    }

}