import java.util.Arrays;

class Solution {
	public int[] solution(long begin, long end) {
		int[] answer = new int[(int) (end - begin + 1)];
		for (int i = 0; i <= end - begin; i++) {
			if (i+begin > 1) answer[i] = 1;
			for (int j = 2; j <= Math.sqrt(i+begin); j++) {
				if ((i+begin) % j == 0) {
					if ((i+begin) / j <= 10_000_000) {
						answer[i] = (int) ((i+begin) / j);
						break;
					} else {
						answer[i] = j;
					}
				}
			}
		}
		return answer;
	}

}