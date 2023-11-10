import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        long[] answer = new long[N];
        // key - value 를 유니온 파인드의 파인드 알고리즘의 자식 - 부모로 생각해보자
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            answer[i] = DFS(room_number[i], map);
        }

        return answer;
    }
    static long DFS(long a, Map<Long, Long> map){
        // 한번도 방문한 적이 없으면 다음 방을 자신의 부모로 설정
        if (!map.containsKey(a)){
            map.put(a, a+1);
            return a;
        }
        // 부모가 지정되지 않을 때까지 부모로 탐색하면서 갱신 (path shortening)
        map.put(a,DFS(map.get(a), map));
        return map.get(a);
    }
}