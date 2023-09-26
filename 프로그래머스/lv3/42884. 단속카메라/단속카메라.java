import java.util.Arrays;

class Solution {
	public int solution(int[][] routes) {
		int answer = 1;

		Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

		int left = routes[0][0];
		int right = routes[0][1];
		for (int i = 1; i < routes.length; i++) {
			left = Math.max(left, routes[i][0]);
			right = Math.min(right, routes[i][1]);
			if (left > right) {
				answer++;
				right = routes[i][1];
			}
		}

		return answer;
	}
}