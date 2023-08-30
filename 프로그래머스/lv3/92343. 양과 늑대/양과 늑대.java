class Solution {
	static int answer = 0;

	public int solution(int[] info, int[][] edges) {
		int N = info.length;
		boolean[][] edge = new boolean[N][N];
//    	for (int i = 0; i < N; i++) {
//    		for (int j = 0; j < N; j++) {
//    			edge[i][j] = -1;
//    		}
//    	}
		for (int i = 0; i < N - 1; i++) {
			edge[edges[i][0]][edges[i][1]] = true;
			edge[edges[i][1]][edges[i][0]] = true;
		}
		boolean[] visited = new boolean[N];
		boolean[] selected = new boolean[N];
		selected[0] = true;
		
		dfs(0, 1, 0, selected, visited, N, info, edge);

		return answer;
	}

	static void dfs(int now, int sheep, int wolf, boolean[] selected, boolean[] visited, int N, int[] info, boolean[][] edge) {
		if (wolf >= sheep)
			return;
	
		answer = Math.max(answer, sheep);	
		visited[now] = true;
		
		for (int i = 0; i < N; i++) {
			if (!edge[now][i]) continue;
			if (visited[i]) continue;
			if (selected[i]) {
				dfs(i, sheep, wolf, selected, visited, N, info, edge);
			} else {
				selected[i] = true;
				if (info[i] == 0)
					dfs(i, sheep+1, wolf, selected, new boolean[N], N, info, edge);
				else
					dfs(i, sheep, wolf+1, selected, visited, N, info, edge);
				selected[i] = false;
			}
		}

	}

	public static void main(String[] args) {
		System.out.println(new Solution().solution(new int[] { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
				new int[][] { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 },
						{ 6, 5 }, { 4, 6 }, { 8, 9 } }));
	}
}