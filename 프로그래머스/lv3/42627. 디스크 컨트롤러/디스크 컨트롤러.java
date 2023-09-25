import java.util.*;

class Solution {
	public int solution(int[][] jobs) {
		int N = jobs.length;

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		int idx = 0;
		int nowTime = jobs[0][0];
		int sum = 0;

		while (idx < N) {
			while (idx < N && jobs[idx][0] <= nowTime) {
				pq.add(jobs[idx++]);
			}

			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				int srt = now[0];
				int time = now[1];

				nowTime += time;
				sum += nowTime - srt;

				while (idx < N && jobs[idx][0] <= nowTime) {
					pq.add(jobs[idx++]);
				}
			}
			
			if (idx < N) nowTime = jobs[idx][0];
		}

		return sum / N;

	}
}