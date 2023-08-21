import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	static int answer = 0;
	static int N;
    public int solution(int k, int[][] dungeons) {
    	N = dungeons.length;
    	ArrayList<int[]> list = new ArrayList<>();
    	for (int i = 0; i < N; i++) {
			list.add(dungeons[i]);
		}
    	
    	
    	boolean[] visited = new boolean[N];
    	dfs(0, k, visited, dungeons);
    	
        return answer;
    }
    public static void dfs(int depth, int k, boolean[] visited, int[][] dungeons) {
    	
    	for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			if (k >= dungeons[i][0]) {
				visited[i] = true;
				dfs(depth+1, k-dungeons[i][1], visited, dungeons);
				visited[i] = false;
			}
		}
    	
    	answer = Math.max(answer, depth);
    	return;
    	
    }
}