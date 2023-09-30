import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int from, to, dist;
        Edge(int from, int to, int dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo (Edge e){
            return this.dist - e.dist;
        }

    }
    static int[] dist;
    static boolean[] visited;
    static  PriorityQueue<Edge> pq;



    // 핵심 아이디어 : 다리를 파괴하지 않았을 때 최단거리로 지나간 경로의 다리를 파괴해야 최단거리가 갱신될 수 있다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        final int INF = 1000 * 1000 + 1;

        int[][] edges = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a][b] = c;
            edges[b][a] = c;
        }

        // 도로를 파괴하지 않았을 때의 최단거리 이동시의 도로 정보를 담을 list
        // 도로를 파괴하지 않은 채 dijkstra를 돌려서 list에 도로 정보를 저장
        int answer = dijkstra(N, edges, false, INF);
        ArrayList<Edge> list = findPath(N, edges, answer);

        // list의 첫번째 값은 pq 시작을 위해 넣은 값으로 무시해도 된다.
        // list의 경로를 끊고 dijkstra를 돌려서 최대값을 찾는다.
        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);
            edges[edge.from][edge.to] = 0;
            edges[edge.to][edge.from] = 0;
            int newDistance = dijkstra(N, edges, true, INF);
            if (newDistance != INF) {
                answer = Math.max(answer, newDistance);
            }
            edges[edge.from][edge.to] = edge.dist;
            edges[edge.to][edge.from] = edge.dist;
        }

        System.out.println(answer);
    }
    static int dijkstra(int N, int[][] edges, boolean flag, int INF){
        dist = new int[N+1];
        Arrays.fill(dist, INF);

        visited = new boolean[N+1];
        pq = new PriorityQueue();

        dist[1] = 0;
//        visited[1] = true;
        pq.add(new Edge(1,1,0));

        while(!pq.isEmpty()){
            Edge nowV = pq.poll();
            int next = nowV.to;

            if (flag && next == N) break; // flag true 시 도착지 도착했으면 탐색 종료
            visited[next] = true;

            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                if (edges[next][i] == 0) continue;
                if (dist[i] > dist[next] + edges[next][i]){
                    dist[i] = dist[next] + edges[next][i];
                    pq.add(new Edge(next, i, dist[i])); // 여기서 시작점부터의 거리가 아니라 간선거리를 넣어서 자꾸 틀림 ...
                }
            }
        }
        return dist[N];
    }

    static ArrayList<Edge> findPath(int N, int[][] edges, int pathLength){
        ArrayList<Edge> list = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        visited = new boolean[N+1];
        queue.add(N);

        while (!queue.isEmpty()){
            int now = queue.poll();
            visited[now] = true;
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                if (edges[now][i] == 0) continue;
                if (dist[i] + edges[now][i] == pathLength){
                    pathLength -= edges[now][i];
                    queue.add(i);
                    list.add(new Edge(now, i, edges[now][i]));
                }
            }
        }
        return list;
    }
}