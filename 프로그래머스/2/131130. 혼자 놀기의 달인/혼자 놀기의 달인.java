import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        int n = cards.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < n; i++){
            if (cards[i] == 0) continue;
            pq.add(group(i, cards));
        }
        if (pq.size() == 1) return 0;
        
        return pq.poll() * pq.poll();
    }
    
    private int group(int idx, int[] cards){
        int count = 0;
        while(cards[idx] != 0){
            count++;
            int next = cards[idx] - 1;
            cards[idx] = 0;
            idx = next;
        }
        return count;
    }
}