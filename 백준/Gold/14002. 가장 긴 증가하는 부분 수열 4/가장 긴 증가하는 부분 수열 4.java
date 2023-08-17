import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		// 2차원 dp를 만든다
		int[][] dp = new int[N][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		dp[0][0] = 1;
		
		
		
		for (int i = 1; i < N; i++) {
			// 1열에는 dp값을, 2열에는 직전에 참조했던 dp의 인덱스를 저장한다.
			dp[i][0] = 1;
			dp[i][1] = i;
			for (int j = i; j >= 0; j--) {
				if (arr[i] > arr[j] && dp[i][0] < dp[j][0] + 1) {
						dp[i][0] = dp[j][0] + 1;
						dp[i][1] = j;
				}
			}
		}
		
		// 가장 긴 증가하는 수열 길이와 그 때의 인덱스값 저장할 변수 선언
		int len = 1;
		int idx = 0;
		// 최대값과 그 때의 인덱스를 찾는다
		for (int i = 0; i < N; i++) {
			if (len < dp[i][0]) {
				len = dp[i][0];
				idx = i;
			}
		}

		
		//출력 코드
		int answer = len;
		int[] answerArr = new int[len];
		
		// 해당 인덱스의 값을 answerArr 배열에 저장하고 직전에 참조했던 dp의 인덱스로 idx를 갱신해준다.
		
		/* 인풋에 대한 dp, idx 예시
		 * 인풋 :
		 * 6
		 * 10 20 10 30 20 50
		 * 
		 * dp : [[1, 0], [2, 0], [1, 2], [3, 1], [2, 2], [4, 3]]
		 * idx : 5 (50) -> 3 (30) -> 1 (20) -> 0 (10)
		 * 
		 * */
		
		while (len > 0) {
			answerArr[--len] = arr[idx];
			idx = dp[idx][1];
		}
		
		System.out.println(answer);
		for (int i : answerArr) {
			System.out.print(i + " ");
		}

	}
}