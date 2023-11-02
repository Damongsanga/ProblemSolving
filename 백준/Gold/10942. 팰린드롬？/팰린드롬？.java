import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] memo = new boolean[N][N];
		
		
		for (int i = 0; i < N; i++) {
			int d = Math.min(i, N-i-1);
			for (int j = 0; j <= d; j++) {
				if (arr[i-j] == arr[i+j]) {
					memo[i-j][i+j] = true;
				} else {
					break;
				}
			}
		}
		
		for (int i = 0; i < N-1; i++) {
			int d = Math.min(i, N-i-2);
			for (int j = 0; j <= d; j++) {
				if (arr[i-j] == arr[i+1+j]) {
					memo[i-j][i+1+j] = true;
				} else {
					break;
				}
			}
		}
		
		
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append((memo[a-1][b-1] ? 1 : 0) + "\n");
		}
		
		System.out.println(sb);
		
		
	}
}
