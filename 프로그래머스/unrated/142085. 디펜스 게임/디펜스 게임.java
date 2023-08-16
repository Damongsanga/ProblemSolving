import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        if (k >= enemy.length) answer = enemy.length;
        
        else { 
        	PriorityQueue<Integer> pq = new PriorityQueue<>();
        	for (int i = 0; i < k; i++) {
				pq.add(enemy[i]);
			}
        	
        	int idx = k;
        	while (true) {
        		
        		// 탈출조건 1 : 병사가 0 이하이거나
        		if (n<0) {
        			answer = idx-1;
        			break;
        		}
        		// 탈출조건 2 : enemy가 남아있지 않거나
        		if (idx == enemy.length) {
        			answer = idx;
        			break;
        		}
        		
        		if (pq.peek() >= enemy[idx]) {
        			n -= enemy[idx];
        		} else {
        			n -= pq.poll();
        			pq.add(enemy[idx]);
        		}
        		idx++;

        	}
        }
        
        
        return answer;
    }

}