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
		
		// 최소값으로 음수 나올 수 있음
		int[] memo = new int[N+1];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Integer.MIN_VALUE;
		}
			
		
		int len = 0;
		for (int i = 0; i < N; i++) {
			// 만약 탐색값이 최대 길이 LIS의 마지막 값보다 크다면 더 긴 LIS의 최소값이 된다.
			if (arr[i] > memo[len]) {
				memo[++len] = arr[i];
				// 아니라면 LIS의 마지막 값들 중 더 작은 값으로 갱신이 가능한지 탐색해본다.
			} else {
				memo[binarySearch(0, len, arr[i], memo)] = arr[i];
			}
		}
		
		System.out.println(len);

	}
	public static int binarySearch(int l, int r, int key, int[] memo) {
		int mid = 0;
		while (l < r) {
			mid = (l + r)/2;
			if (memo[mid] >= key) {
				r = mid;
			} else {
				l = mid +1;
			}
		}
		return l;
	}
}

