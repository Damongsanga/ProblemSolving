import java.util.*;

class Solution {
	static int answer = 0;
	static ArrayList<Integer> keys = new ArrayList<>();

	public int solution(String[][] relation) {
		int N = relation.length; // 학생 수 (최대 20)
		int M = relation[0].length; // key 수 (최대 8)
		for (int i = 1; i <= M; i++) {
			dfs(0, 0, 0, i, N, M, relation);
		}
//		System.out.println(keys.toString());
		return answer;
	}

	static void dfs(int depth, int srt, int now, int count, int N, int M, String[][] relation) {
//		System.out.println(now);
		if (depth == count) {
//			System.out.println("final : " + now);
			if (check(relation, now, N, M)) {
				keys.add(now);
				answer++;
//				System.out.println("정답");
			}
			return;
		}

		for (int i = srt; i < M; i++) {
			now = now | (1 << i);
			if (check2(now))
				dfs(depth + 1, i+1, now, count, N, M, relation);
			now -= (1 << i);
		}
	}

	static boolean check(String[][] relation, int now, int N, int M) {
		if (now == (1 << M) - 1)
			return true;

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
	
	static boolean check2(int now) {
		for (int key : keys) {
			if ((now & key) == key)
				return false;
		}
		return true;
	}

}