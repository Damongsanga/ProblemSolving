import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int e; int dist;

        public Edge(int e, int dist){
            this.e = e;
            this.dist = dist;
        }

        public int compareTo(Edge e){
            return this.dist - e.dist;
        }
    }

    static final int MAX = 987654322;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점
            int m = Integer.parseInt(st.nextToken()); // 간선
            int t = Integer.parseInt(st.nextToken()); // 후보 정점

            List<Edge>[] edges = new List[n+1];
            for (int i = 0; i < n+1; i++) {
                edges[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작
            int g = Integer.parseInt(st.nextToken()); // 여기랑
            int h = Integer.parseInt(st.nextToken()); // 여기 사이 지나감

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to, dist));
                edges[to].add(new Edge(from, dist)); // 양방향 간선
            }

            int[] candidates = new int[t];
            for (int i = 0; i < t; i++) {
                candidates[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(candidates);

            int[] distArr = new int[n+1];
            Arrays.fill(distArr, MAX);

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(s, 0));
            distArr[s] = 0;

            while(!pq.isEmpty()){
                Edge now = pq.poll();

                for (Edge next : edges[now.e]){
                    if (distArr[next.e] > now.dist + next.dist * 2){
                        distArr[next.e] = now.dist + next.dist * 2;
                        if ((now.e == g && next.e == h) || (now.e == h && next.e == g)) {
                            distArr[next.e]--;
                        }
                        pq.add(new Edge(next.e, distArr[next.e]));
                    }

                    else if (distArr[next.e] == now.dist + next.dist * 2){
                        if ((now.e == g && next.e == h) || (now.e == h && next.e == g)) {
                            distArr[next.e]--;
                            pq.add(new Edge(next.e, distArr[next.e]));
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int candidate : candidates) {
                if (distArr[candidate] % 2 == 1){
                    sb.append(candidate).append(" ");
                }
            }
            System.out.println(sb);

        }

    }

}