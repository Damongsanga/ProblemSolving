import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    static class Dij {
        int dist; int cur; int trapVisited;

        public Dij(int dist, int cur, int trapVisited) {
            this.dist = dist;
            this.cur = cur;
            this.trapVisited = trapVisited;
        }

        @Override
        public String toString() {
            return "Dij{" +
                    "dist=" + dist +
                    ", cur=" + cur +
                    ", trapVisited=" + Integer.toBinaryString(trapVisited) +
                    '}';
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        final int INF = 987654321;
        int[][][] routes = new int[n+1][n+1][2];

//        System.out.println(Arrays.deepToString(roads));

        for (int[] road : roads){
            if (routes[road[0]][road[1]][0] == 0 || routes[road[0]][road[1]][0] > road[2]){
                routes[road[0]][road[1]][0] = road[2];
                routes[road[1]][road[0]][1] = road[2];
            }
        }

//        System.out.println(Arrays.deepToString(routes));

        Arrays.sort(traps);
        int[][] visited = new int[n+1][1<<traps.length];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(visited[i], INF);
        }

        PriorityQueue<Dij> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        pq.add(new Dij(0, start, 0));

        while(!pq.isEmpty()){
            Dij now = pq.poll();
            if (now.cur == end){
                answer = now.dist;
                break;
            }

            int idx = Arrays.binarySearch(traps, now.cur);
            boolean isTrap = false;
            if (idx >= 0) {
                now.trapVisited = now.trapVisited ^ (1 << idx);
                if ((now.trapVisited & (1<<idx)) > 0)
                isTrap = true;
            }

            for (int next = 1; next <= n; next++) {
                if (next == now.cur) continue;
                int idxNext = Arrays.binarySearch(traps, next);
                boolean isTrapNext = idxNext >= 0;
                boolean isVisitedTrapNext = isTrapNext & (now.trapVisited & (1 << idxNext)) > 0;
                int r = routes[now.cur][next][isVisitedTrapNext ^ isTrap? 1 : 0];
                if (r == 0) continue;
                if (isTrap && isTrapNext) visited[next][now.trapVisited] = INF;
                if (visited[next][now.trapVisited] < now.dist + r) continue;
                visited[next][now.trapVisited] = now.dist + r;
                pq.add(new Dij(now.dist + r, next, now.trapVisited));
            }
        }

        return answer;
    }


}