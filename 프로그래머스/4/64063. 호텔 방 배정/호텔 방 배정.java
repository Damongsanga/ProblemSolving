import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        long[] answer = new long[N];
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            answer[i] = DFS(room_number[i], map);
        }

        return answer;
    }
    static long DFS(long a, Map<Long, Long> map){
        if (!map.containsKey(a)){
            map.put(a, a+1);
            return a;
        }
        map.put(a,DFS(map.get(a), map));
        return map.get(a);
    }
}