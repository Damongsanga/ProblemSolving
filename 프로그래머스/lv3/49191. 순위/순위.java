import java.util.*;

class Solution {
	public int solution(int n, int[][] results) {
		int answer = 0;
		int[][] dist = new int[n + 1][n + 1];

		for (int i = 0; i < results.length; i++) {
			int[] tmp = results[i];
			dist[tmp[0]][tmp[1]] = 1;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				for (int k = 1; k < n + 1; k++) {
					dist[j][k] = dist[j][i] == 1 && dist[i][k] == 1 ? 1 : dist[j][k];
				}
			}
		}
		
		for (int i = 1; i < n + 1; i++) { // 선수
			int count = 0;
			for (int j = 1; j < n + 1; j++) { // 다른 선수
				if (dist[i][j] == 1 || dist[j][i] == 1) count++;
			}
			if (count == n-1) answer++;
		}

		return answer;
	}
}