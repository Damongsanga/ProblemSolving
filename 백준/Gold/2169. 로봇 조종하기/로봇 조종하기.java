import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N][M][2];
		
		dp[0][0][0] = arr[0][0]; 
		dp[0][0][1] = arr[0][0]; 
		for (int i = 1; i < M; i++) {
			dp[0][i][0] += arr[0][i] + dp[0][i-1][0];
			dp[0][i][1] += arr[0][i] + dp[0][i-1][1];
		}
		
		for (int i = 1; i < N; i++) {
			dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1]) + arr[i][0];
			for (int j = 1; j < M; j++) {
				dp[i][j][0] = Math.max(Math.max(dp[i-1][j][0], dp[i-1][j][1]), dp[i][j-1][0]) + arr[i][j];
			}
			dp[i][M-1][1] = Math.max(dp[i-1][M-1][0], dp[i-1][M-1][1]) + arr[i][M-1];
			for (int j = M-2; j >= 0; j--) {
				dp[i][j][1] = Math.max(Math.max(dp[i-1][j][0], dp[i-1][j][1]), dp[i][j+1][1]) + arr[i][j];
			}
		}
		
		System.out.println(Math.max(dp[N-1][M-1][0], dp[N-1][M-1][1]));
	
	}
}
