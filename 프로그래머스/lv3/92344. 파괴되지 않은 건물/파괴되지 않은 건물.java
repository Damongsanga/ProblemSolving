
class Solution {
	public int solution(int[][] board, int[][] skill) {
		int N = board.length;
		int M = board[0].length;
		int[][] arr = new int[N + 1][M + 1];

		for (int i = 0; i < skill.length; i++) {
			int t = skill[i][5];
			if (skill[i][0] == 2) {
				arr[skill[i][1]][skill[i][2]] += t;
				arr[skill[i][1]][skill[i][4] + 1] -= t;
				arr[skill[i][3] + 1][skill[i][2]] -= t;
				arr[skill[i][3] + 1][skill[i][4] + 1] += t;
			} else {
				arr[skill[i][1]][skill[i][2]] -= t;
				arr[skill[i][1]][skill[i][4] + 1] += t;
				arr[skill[i][3] + 1][skill[i][2]] += t;
				arr[skill[i][3] + 1][skill[i][4] + 1] -= t;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M; j++) {
				arr[i][j] += arr[i][j - 1];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M; j++) {
				arr[j][i] += arr[j - 1][i];
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] + arr[i][j] > 0)
					count++;
			}
		}

		return count;
	}
}