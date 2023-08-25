import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int max = arr[N-1];
			long answer = 0;
			for (int i = N-2; i >= 0; i--) {
				if (arr[i] <= max) answer += (max - arr[i]);
				else max = arr[i];
			}
			
			System.out.printf("#%d %d \n",test_case, answer);
		
		}
	}
}