import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int sc : scoville){
            pq.add(sc);
        }
        
        while(pq.size() > 1 && pq.peek() < K){
            int A = pq.poll();
            int B = pq.poll();
            pq.add(A + B*2);
            answer++;
        }
        
        return pq.size() == 1 && pq.peek() < K ? -1 : answer;
    }
}