import java.util.Arrays;

class Solution {
	static int[] answer = new int[11];
	static int answersum = 0;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(new Solution().solution(1, new int[] {0,0,0,0,0,0,0,0,0,0,1})));
	}
	
	public int[] solution(int n, int[] info) {
		backtracking(0, n, new int[11], info);
		return answersum == 0 ? new int[] { -1 } : answer;
	}

	
	static void backtracking(int depth, int n, int[] ryan, int[] info) {
		
		// 깊이가 10이면 남은 화살을 모두 0으로 넣고 점수 계산
		if (depth == 10 || n == 0) {
			ryan[10] = n;
			int diff = diff(ryan, info);
			if (diff > answersum) {
				answer = Arrays.copyOf(ryan, ryan.length);
				answersum = diff;
			// 점수차이가 기존 답과 같으면 더 낮은 화살 수가 많은지 체크
			} else if (diff == answersum) {
				if (mincheck(ryan))
					answer = Arrays.copyOf(ryan, ryan.length);
			}
			// 원복 필요!
			ryan[10] = 0;
			return;
		}

		// 화살을 못맞추고 넘어가는 경우 먼저 고려
		backtracking(depth + 1, n, ryan, info);
		// 어피치보다 많이 할 수 잇는 경우
		if (n > info[depth]) {
			ryan[depth] = info[depth] + 1;
			backtracking(depth + 1, n - info[depth] - 1, ryan, info);
			ryan[depth] = 0;
		// 불가능한 경우 바로 depth 10으로 이동 (성능 개선)
		} else {
			backtracking(10, n, ryan, info);
		}
	}

	// 두 선수의 점수 차이 
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
	
	// 전의 정답과 비교하여 더 낮은 점수의 화살이 더 많은지 체크
	static boolean mincheck(int[] ryan) {
		for (int i = 10; i >= 0; i--) {
			if (answer[i] > ryan[i]) return false;
			else if (answer[i] < ryan[i]) return true;
		}
		return false;
	}
}