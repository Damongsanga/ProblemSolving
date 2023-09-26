import java.util.*;

class Solution {
	public int solution(int distance, int[] rocks, int n) {
		// 입력 정렬
		Arrays.sort(rocks);
		
		// 이분 탐색
		int answer = 0;
		int min = 1;
		int max = distance;
		
		while (min <= max) {
			int mid = min + (max - min) / 2;
			if (isPossible(mid, distance, rocks, n)) {
				answer = mid;
				min = mid + 1;
			} else {
				max = mid-1;
			}
		}

		return answer;
	}

	
	// 왼쪽, 오른쪽의 거리 차이로 해당 값 (mid)이 적합한 값인지 이분탐색
	static boolean isPossible(int mid, int distance, int[] rocks, int n) {
		// 
		int leftRock = rocks.length; // 남아있는 돌
		int eraseRock = n; // 지워야 하는 돌
		int idx = 0;
		int left = 0;
		
		while (idx < rocks.length) {
			
			// 만약 남아있는 돌과 지워야 할 돌의 수가 같다면 현재위치와 도착지점의 차이로 possible 여부 리턴
			if (leftRock == eraseRock) {
				if (distance - left >= mid) return true;
				else return false;
			}
			
			int right = rocks[idx];
			
			if (right - left >= mid) {
				left = rocks[idx++];
			} else {
				idx++;
				if (eraseRock == 0) return false; // 더이상 돌을 지울 수 없으면 false 리턴
				eraseRock--;
			}
			
			leftRock--; // 추가로 봐야하는 돌을 1개씩 줄여야
		}
		
		// 도착지점과의 비교
		int right = distance;
		if (right - left >= mid) return true;
		return false;
	}

}