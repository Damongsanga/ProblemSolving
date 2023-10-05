import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static final long INF = Long.MAX_VALUE/2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edge = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            edge[from].add(new Edge(to, d)); // 단방향
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        if (BF(dist, edge, N)) {
            for (int i = 2; i <= N; i++) {
                sb.append((dist[i] == INF? -1 : dist[i] )+ "\n");
            }
        }

        else sb.append("-1\n");
        System.out.println(sb);

    }

    static boolean BF(long[] dist, ArrayList<Edge>[] edge, int N) {
        dist[1] = 0;
        boolean updated = false;
        // 정점의 개수 -1 보다 1개 (즉, 정점 개수만큼 돈다) 많이 돈다
        for (int i = 0; i < N; i++) {
            updated = false;
            // 전체 정점에 대하여 모든 간선을 구한다
            for (int j = 1; j <= N; j++) {
                if (dist[j] == INF) continue;
                for (Edge e : edge[j]) {
                    if (dist[e.to] > dist[j] + e.dist) {
                        dist[e.to] = dist[j] + e.dist;
                        updated = true;
                    }
                }
            }
            if (!updated)
                break;
        }

        // 만약 정점의 개수만큼 돌았는데도 update가 되었다는 것은 사이클이 존재한다는 것
        return updated ? false : true;
    }
}