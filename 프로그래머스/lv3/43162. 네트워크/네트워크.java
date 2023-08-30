class Solution {
	static int[] parents; 
	
    public int solution(int n, int[][] computers) {
    	int m = computers.length;
    	
    	parents = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		parents[i] = i;
    	}
    	
    	int answer = n;
    	for (int i = 0; i < m; i++) {
    		for (int j = i+1; j < m; j++) {
    			if (computers[i][j] == 0) continue;
    			if (union(i, j)) {
    				answer--;
    			}
    		}
    	}
    	
        
        return answer;
    }
    
    public boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b); 
    	
    	if (aRoot == bRoot) return false;
    	parents[aRoot] = bRoot;
    	return true;
    	
    }
    
    public int find(int a) {
    	if (a == parents[a]) return a;
    	return parents[a] = find(parents[a]);
    }
}