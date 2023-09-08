import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		long[][] arr = new long[N+1][N+1];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
//		System.out.println(Arrays.deepToString(arr));
		for (int i =0; i < N; i++) {
			for (int j = N; j > 0; j--) {
				arr[j][i] -= arr[j-1][i];
			}
		}
		
//		System.out.println(Arrays.deepToString(arr));
		
		for (int i =0; i < N; i++) {
			for (int j = N; j > 0; j--) {
				arr[i][j] -= arr[i][j-1];
			}
		}

//		System.out.println(Arrays.deepToString(arr));
		
		long[][] answer = new long[N][N];
		
		for (int i = 0; i+M < N+1; i++) {
			for (int j = 0; j+M < N+1; j++) {
				long x = arr[i][j];
				answer[i+M/2][j+M/2] -= x;
				arr[i][j] -= x;
				arr[i+M][j] += x;
				arr[i][j+M] += x;
				arr[i+M][j+M] -= x;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
}
