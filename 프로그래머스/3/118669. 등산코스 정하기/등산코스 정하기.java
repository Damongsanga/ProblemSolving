import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static class Node{
        int cur; int intensity;

        public Node(int cur, int intensity) {
            this.cur = cur;
            this.intensity = intensity;
        }
    }

    static class Path {
        int from; int to; int dist;

        public Path(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int INF = 987654321;
        int[] answer = new int[2];
        Arrays.fill(answer, INF);
        ArrayList<Path>[] map= new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for(int[] path : paths){
            map[path[0]].add(new Path(path[0], path[1], path[2]));
            map[path[1]].add(new Path(path[1], path[0], path[2]));
        }

        int[] visited = new int[n+1];
        Arrays.fill(visited, INF);

        Arrays.sort(summits);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> {
            if (o1.intensity == o2.intensity) return o1.cur - o2.cur;
            return o1.intensity - o2.intensity;
        });

        for(int gate : gates){
            pq.add(new Node(gate, 0));
            visited[gate] = 0;
        }

        while (!pq.isEmpty()){
            Node now = pq.poll();
            if(now.intensity > answer[1]) continue;

            int idx = Arrays.binarySearch(summits, now.cur);
            if (idx >= 0){
                if (summits[idx] < answer[0]) {
                    answer[0] = summits[idx];
                    answer[1] = now.intensity;
                }
                continue;
            }

            for (Path path : map[now.cur]){
                int newIntensity = Math.max(path.dist, now.intensity);
                if (visited[path.to] > newIntensity){
                    visited[path.to] = newIntensity;
                    pq.add(new Node(path.to, newIntensity));
                }
            }

        }

        return answer;
    }
}