import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int S = set.size();
        int N = gems.length;
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        int l = 0;
        int r = 1;
        int len = N+1;
        int[] answer = {l+1,r};
        for (int i = 0; i < N; i++) {
            String gem = gems[i];
            if (!map.containsKey(gem)){
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                pq.add(i);
                map.put(gem, pq);
            } else {
                PriorityQueue<Integer> pqTmp = map.get(gem);
                while (pqTmp.size() >= 1 && pqTmp.peek() == l){
                    pqTmp.poll();
                    l++;
                }
                pqTmp.add(i);
            }
            r = i+1;
            if (map.keySet().size() == S){
                while(map.get(gems[l]).size() > 1 && map.get(gems[l]).peek() == l){
                    map.get(gems[l++]).poll();
                }
                if (r-l < len){
                    answer[0] = l+1;
                    answer[1] = r;
                    len = r-l;
                }
            }
        }

        return answer;
    }

}