import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> mapNext = new HashMap<>();
        Map<Integer, List<Integer>> mapPrev = new HashMap<>();
        for (int[] edge : edges) {
            mapNext.putIfAbsent(edge[0], new ArrayList<>());
            mapNext.get(edge[0]).add(edge[1]);
            mapPrev.putIfAbsent(edge[1], new ArrayList<>());
            mapPrev.get(edge[1]).add(edge[0]);
        }

        int[] answer = new int[4];

        int center = 0;
        for (Integer i : mapNext.keySet()) {
            if (mapPrev.get(i) == null && mapNext.get(i).size() >=2){
                center = i;
                answer[0] = i;
                break;
            }
        }

        for (Integer i : mapNext.get(center)) {
            bfs(i, mapNext, mapPrev,answer);
        }

        return answer;
    }

    private static void bfs(int start, Map<Integer, List<Integer>> mapNext, Map<Integer, List<Integer>> mapPrev, int[] answer){
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int now = queue.poll();
            if(!visited.add(now)) break;
            if (mapNext.get(now) == null){
                answer[2]++;
                return;
            }
            if (mapNext.get(now).size() > 1) {
                answer[3]++;
                return;
            }

            queue.add(mapNext.get(now).get(0));
        }
        answer[1]++;
    }
}