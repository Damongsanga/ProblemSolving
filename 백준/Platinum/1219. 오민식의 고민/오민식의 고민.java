import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, d;

        Edge(int to, int d) {
            this.to = to;
            this.d = d;
        }

    }

    static final int NINF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int srt = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, dist));
        }

        int[] city = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }

        long[] dist = new long[N];
        Arrays.fill(dist, NINF);
        dist[srt] = city[srt];

        // 벨만 포드
        boolean updated;
        for (int i = 0; i < N - 1; i++) {
            updated = false;
            for (int j = 0; j < N; j++) {
                if (dist[j] == NINF) continue;
                for (Edge e : edges[j]) {
                    int to = e.to;
                    int d = e.d;
                    if (dist[to] < dist[j] - d + city[to]) {
                        updated = true;
                        dist[to] = dist[j] - d + city[to];
                    }
                }
            }
            if (!updated) break;
        }

        // 사이클에 해당하는 도시 리스트에 저장
        ArrayList<Integer> cycledList = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            if (dist[j] == NINF) continue;
            for (Edge e : edges[j]) {
                int to = e.to;
                int d = e.d;
                if (dist[to] < dist[j] - d + city[to]) {
                    dist[to] = dist[j] - d + city[to];
                    cycledList.add(to);
                }
            }
        }


        if (dist[end] == NINF) {
            System.out.println("gg");
        } else if (BFS(cycledList, edges, srt, end, N)) { // end가 갱신되었으면 srt ~ end 사이 사이클이 있는게 아닌가..?
            System.out.println("Gee");
        } else {
            System.out.println(dist[end]);
        }
    }

    static boolean BFS(ArrayList<Integer> cycledList, ArrayList<Edge>[] edges, int srt, int end, int N){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        for(Integer i:cycledList){
            queue.add(i);
            visited[i] = true;
        }
        while(!queue.isEmpty()){
            int now = queue.poll();
            if (now == srt || now == end) return true;

            for(Edge e : edges[now]){
                if (visited[e.to]) continue;
                visited[e.to] = true;
                queue.add(e.to);
            }
        }
        return false;
    }

}