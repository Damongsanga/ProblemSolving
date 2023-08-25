import java.util.Arrays;

class Solution {
	static int[] answer = new int[11];
	static int answersum = 0;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new Solution().solution(1, new int[] {0,0,0,0,0,0,0,0,0,0,1})));
	}
	
	public int[] solution(int n, int[] info) {
		dfs(0, n, new int[11], info);
		return answersum == 0 ? new int[] { -1 } : answer;
	}

	static void dfs(int depth, int n, int[] ryan, int[] info) {
		
		if (depth == 10 || n == 0) {
			ryan[10] = n;
			int diff = diff(ryan, info);
			if (diff > answersum) {
				answer = Arrays.copyOf(ryan, ryan.length);
				answersum = diff;
			} else if (diff == answersum) {
				if (mincheck(ryan))
					answer = Arrays.copyOf(ryan, ryan.length);
			}
			ryan[10] = 0;
			return;
		}

		dfs(depth + 1, n, ryan, info);
		if (n > info[depth]) {
			ryan[depth] = info[depth] + 1;
			dfs(depth + 1, n - info[depth] - 1, ryan, info);
			ryan[depth] = 0;
		} else {
			dfs(10, n, ryan, info);
		}
	}

	static int diff(int[] ryan, int[] info) {
		int diff = 0;
		for (int i = 0; i <= 10; i++) {
			if (ryan[i] == 0 && info[i] == 0) continue;
			if (ryan[i] > info[i])
				diff += (10-i);
			else
				diff -= (10-i);
		}
		return diff;
	}
	
	
	static boolean mincheck(int[] ryan) {
		for (int i = 10; i >= 0; i--) {
			if (answer[i] > ryan[i]) return false;
			else if (answer[i] < ryan[i]) return true;
		}
		return false;
	}
}