import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        ArrayList<Integer>[] edges = new ArrayList[n];
        int[] orders = new int[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] p: path) {
            edges[p[0]].add(p[1]);
            edges[p[1]].add(p[0]);
        }

        boolean[] isNotValid = new boolean[n];
        for (int[] o: order) {
            orders[o[0]] = o[1];
            isNotValid[o[1]] = true; // true 이면 못감
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        if (isNotValid[0]) return false;
        queue.add(0);
        visited[0] = true;
        isNotValid[0] = false;

        while(!queue.isEmpty()){
            int now = queue.poll();
            count++;

            int nextOrd = orders[now];
            if (nextOrd != 0){
                isNotValid[nextOrd] = false;
                for(int j : edges[nextOrd]){
                    if (visited[j]) {
                        queue.add(nextOrd);
                        visited[nextOrd] = true;
                        break;
                    }
                }
            }

            for (int i : edges[now]) {
                if (visited[i]) continue;
                if (isNotValid[i]) continue;
                visited[i] = true;
                queue.add(i);
            }

        }

        return count == n;
    }
}