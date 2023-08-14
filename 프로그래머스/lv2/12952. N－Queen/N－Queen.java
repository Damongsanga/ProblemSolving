class Solution {
	static int answer = 0;
    public int solution(int n) {
        int[] arr = new int[n+1];
        backtracking(1, arr, n);
        return answer;
    }
    public static void backtracking(int depth, int[] arr, int n) {
    	if (depth == n+1) {
    		answer++;
    		return;
    	}
    	
    	for (int i = 0; i < n; i++) {
			if (queenable(depth, i, arr)) {
				arr[depth] = i;
				backtracking(depth+1, arr, n);
				arr[depth] = 0;
			}
		}
    	
    }
    public static boolean queenable(int depth, int i, int[] arr){
    	for (int j = 1; j < depth; j++) {
    		if (i == arr[j]) return false;
			if (Math.abs(i - arr[j]) == depth-j) return false;
		}
    	return true;
    }
    
}
