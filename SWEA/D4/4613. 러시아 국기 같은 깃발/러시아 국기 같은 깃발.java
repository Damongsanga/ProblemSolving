import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] arr = new int[N][3];
			
			// 누적합 배열 구현
			for (int i = 0; i < N; i++) {
				char[] tmp = sc.next().toCharArray();
				for (int j = 0; j < M; j++) {
					switch (tmp[j]) {
					case ('W'):
						arr[i][0]++;
						break;
					case ('B'):
						arr[i][1]++;
						break;
					case ('R'):
						arr[i][2]++;
					}
				}
				if (i == 0) continue;
				for (int j = 0; j < 3; j++) {
					arr[i][j] += arr[i-1][j]; // arr는 첫 행부터 해당 행까지의 하양, 파랑, 빨강의 누적수를 저장하게 된다.
				}
			}
			
			int answer = 987654321;
			for (int i = 0; i < N-2; i++) { // 2칸 전까지 가능 (Blue 최소 한줄)
				for (int j = i+1; j<N-1; j++) { // 1칸 전까지 가능 (Red 최소 1줄)
					int white = arr[i][1] + arr[i][2]; // i번째까지의 파랑, 빨강의 합
					int blue = (arr[j][0] - arr[i][0]) + (arr[j][2] - arr[i][2]); // i+1 ~ j 번째까지의 하양, 빨강의 합
					int red = (arr[N-1][0] - arr[j][0]) + (arr[N-1][1] - arr[j][1]); // j+1 ~ N-1 (마지막)번째까지의 하양, 파랑의 합
					answer = Math.min(answer, white + blue + red); // 위 3개를 합하면 총 덧칠해야하는 수이다. 이 값이 기존 답보다 작으면 갱신
				}
			}

			System.out.printf("#%d %d \n", test_case, answer);
		}
	}
}