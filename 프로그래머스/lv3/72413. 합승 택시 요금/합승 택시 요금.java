import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    static class Edge implements Comparable<Edge>{
        int from, to, dist;
        Edge(int from, int to, int dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge e){
            return this.dist - e.dist;
        }
    }


    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 200 * 100_000 + 1;

        // 간접리스트화
        ArrayList<Edge>[] edges = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < fares.length; i++) {
            edges[fares[i][0]].add(new Edge(fares[i][0], fares[i][1], fares[i][2]));
            edges[fares[i][1]].add(new Edge(fares[i][1], fares[i][0], fares[i][2]));
        }

        int[] S = dijkstra(s, edges, n , INF);
        int[] A = dijkstra(a, edges, n , INF);
        int[] B = dijkstra(b, edges, n , INF);

        int max = INF * 3;
        for (int i = 1; i <= n; i++) {
            max = Math.min(max, S[i] + A[i] + B[i]);
        }

        return max;
    }

    static int[] dijkstra(int srt, ArrayList<Edge>[] edges, int n, int INF){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        boolean[] visited = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(srt, srt, 0));
        dist[srt] = 0;
        while(!pq.isEmpty()){

            Edge E = pq.poll();
            int now = E.to;
            visited[now] = true;

            ArrayList<Edge> list = edges[E.to];
            for (int i = 0; i < list.size(); i++) {
                int next = list.get(i).to;
                if (visited[next]) continue;
                if (dist[next] > dist[now] + list.get(i).dist){
                    dist[next] = dist[now] + list.get(i).dist;
                    pq.add(new Edge(now, next, dist[next]));
                }
            }
        }

        return dist;

    }

}