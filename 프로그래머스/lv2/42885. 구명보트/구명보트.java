import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < people.length; i++) {
			deque.add(people[i]);
		}
        
        while(!deque.isEmpty()) {
        	int weight = limit - deque.pollLast();
        	if (!deque.isEmpty() && deque.peekFirst() <= weight) deque.pollFirst();
        	answer++;
        }
        
        return answer;
    }
}