import java.util.*;

class Solution {
	static int answer = 0;
	static ArrayList<Integer> keys = new ArrayList<>(); // 가능한 후보키 조합 저장

	public int solution(String[][] relation) {
		int N = relation.length; // 학생 수 (최대 20)
		int M = relation[0].length; // key 수 (최대 8)
		
		// 조합의 원소 수가 작은 순서대로 판단 필요함 
		for (int i = 1; i <= M; i++) {
			dfs(0, 0, 0, i, N, M, relation);
		}
		return answer;
	}

	static void dfs(int depth, int srt, int now, int count, int N, int M, String[][] relation) {
		// 지정한 갯수만큼 선택되었으면 유일성 판단하여 성립하면 조합 저장, 답++
		if (depth == count) {
			if (uniqueness(relation, now, N, M)) {
				keys.add(now);
				answer++;
			}
			return;
		}

		// visited 배열 사용하지 않고 srt를 조절하여 구현
		for (int i = srt; i < M; i++) {
			now = now | (1 << i);
			if (minimality(now)) // 최소성 판단
				dfs(depth + 1, i+1, now, count, N, M, relation);
			now -= (1 << i);
		}
	}

	// 유일성 판단
	static boolean uniqueness(String[][] relation, int now, int N, int M) {
		if (now == (1 << M) - 1)
			return true;

		// Set으로 중복여부 판단
		Set<String> set = new HashSet<>();
		for (int i = 0; i < relation.length; i++) {
			String tmp = null;
			for (int j = 0; j < M; j++) {
				if ((now & (1 << j)) > 0)
					tmp += relation[i][j];
			}
			set.add(tmp);
		}
		return set.size() == N;
	}
	
	// 최소성 판단
	static boolean minimality(int now) {
		// 현재 조합의 부분집합에 키가 존재하면 최소성 성립하지 않음
		for (int key : keys) {
			if ((now & key) == key)
				return false;
		}
		return true;
	}

}