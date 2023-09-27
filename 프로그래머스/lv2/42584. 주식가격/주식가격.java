import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
    	int T = prices.length;
        int[] answer = new int[T];
        Stack<Integer[]> stack = new Stack<>();
        
        
        for (int i = 0; i < T; i++) {
			int stockNowPrice = prices[i];
			while (!stack.isEmpty() && stack.peek()[0] > stockNowPrice) {
				Integer[] stockPrev = stack.pop();
				answer[stockPrev[1]] = i - stockPrev[1];
			}
			stack.add(new Integer[] {stockNowPrice, i});
		}
        
        while (!stack.isEmpty()) {
			Integer[] stockPrev = stack.pop();
			answer[stockPrev[1]] = T - stockPrev[1] - 1;
		}
        
        
        return answer;
    }
}