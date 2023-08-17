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
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1번째 열은 해당 숫자를 사용하여 증가할 때
		// 2번째 열은 해당 숫자를 사용하여 감소할 때,이 때부터 감소하는 것 포함
		int[][] dp = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1; dp[i][1] = 1;
			for (int j = i-1; j >= 0; j--) {
				// 해당 숫자가 전 숫자들보다 크다면 더 증가하는 경우만 가능
				if (arr[i] > arr[j]) {
					dp[i][0] = Math.max(dp[i][0], dp[j][0]+1);
				// 해당 숫자가 전 숫자들 보다 작으면 직전까지 증가했다가 감소하거나, 이어서 감소하는 경우 총 2가지 가능
				} else if (arr[i] < arr[j]) {
					dp[i][1] = Math.max(dp[i][1], dp[j][0]+1);
					dp[i][1] = Math.max(dp[i][1], dp[j][1]+1);
				}
			}		
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);

	}

}