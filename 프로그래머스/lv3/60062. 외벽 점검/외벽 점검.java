import java.util.Arrays;

class Solution {
	
	static int N;
	static int M;
	static int answer;
	
	public int solution(int n, int[] weak, int[] dist) {
		N = weak.length; M = dist.length;
		boolean[] visited = new boolean[N];
		int[] betweenWeak = new int[N];
		for (int i = 0; i < N; i++) {
			if (i == N-1) {
				betweenWeak[i] = weak[0] - weak[i] + n;
				continue;
			} 
			betweenWeak[i] = weak[i+1] - weak[i];
		}
//		System.out.println(Arrays.toString(betweenWeak));
		
		
		answer = 987654321;
		dfs(0, M-1, visited, n, weak, betweenWeak, dist);
		return answer == 987654321 ? -1 : answer;
	}

	static void dfs(int count, int nowDist, boolean[] visited, int n, int[] weak, int[] betweenWeak, int[] dist) {
//		
//		
//		System.out.println(Arrays.toString(visited));
//		System.out.println("count : " + count + " nowDist : " + nowDist);
		
		if (count >= N) {
			answer = Math.min(answer, M - nowDist - 1);
//			System.out.println("answer : " + answer);
			return;
		}
		
		if (nowDist == -1) {
			return;
		}

		if (M - nowDist - 1 > answer) {
			return;
		}
		
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			count++;
			
			// 정방향 탐색
			boolean[] copyVisited = Arrays.copyOf(visited, N);
			int idx = i;
			int nextIdx = (idx + 1 + N) % N;
			int move = dist[nowDist];
			int count_tmp = 0;
			
			while (!copyVisited[nextIdx] && move >= betweenWeak[idx]) {
				move -= betweenWeak[idx];
				idx = nextIdx;
				nextIdx = (idx + 1 + N) % N;
				copyVisited[idx] = true;
				count_tmp++;
			}

			dfs(count + count_tmp, nowDist-1, copyVisited, n, weak, betweenWeak, dist);
			
//			// 역방향 탐색
//			copyVisited = Arrays.copyOf(visited, N);
//			idx = i;
//			nextIdx = (idx - 1 + N) % N;
//			move = dist[nowDist];
//			count_tmp = 0;
//
//			while (!copyVisited[nextIdx] && move >= betweenWeak[nextIdx]) {
//				move -= betweenWeak[nextIdx];
//				idx = nextIdx;
//				nextIdx = (idx - 1 + N) % N;
//				copyVisited[idx] = true;
//				count_tmp++;
//			}
//			dfs(count + count_tmp, nowDist-1, copyVisited, n, weak, betweenWeak, dist);
			
			visited[i] = false;
			count--;
			
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Solution().solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 }));
		System.out.println(new Solution().solution(12, new int[] { 1, 3, 4, 5, 10 }, new int[] { 3, 5, 7 }));
		System.out.println(new Solution().solution(12, new int[] { 1, 3, 4, 9, 10 }, new int[] { 3, 5, 7 }));
		System.out.println(new Solution().solution(100, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 99 }, new int[] { 50 }));
		System.out.println(new Solution().solution(2, new int[] { 0, 1 }, new int[] { 2 }));
		System.out.println(new Solution().solution(5, new int[] { 0, 1, 3 }, new int[] { 2 }));
	}
}