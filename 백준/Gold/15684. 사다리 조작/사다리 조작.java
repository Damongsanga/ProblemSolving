import java.util.Scanner;

public class Main {

	static int answer = 987654321;
	static int N;
	static int H;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		H = sc.nextInt();
		int[][] arr = new int[N + 1][H + 1]; // 1~N-1까지가 사다리임, 양쪽으로 한칸씩 빈공간 존재
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[b][a] = 1; // 사다리
			arr[b - 1][a] = -1; // 양쪽은 사다리를 둘 수 없음
			arr[b + 1][a] = -1; // 양쪽은 사다리를 둘 수 없음
		}

		backtracking(0, 0, arr);

		System.out.println(answer == 987654321 ? -1 : answer);

	}

	static void backtracking(int depth, int count, int[][] arr) {
		
		if (depth == 3) {
			if (isPossible(arr)) {
				answer = Math.min(count, answer);
			}
			return;
		}
		
		backtracking(depth+1, count, arr);
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= H; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					int tmp = arr[i+1][j];
					arr[i+1][j] = -1;
					int tmp2 = arr[i-1][j];
					arr[i-1][j] = -1;
					backtracking(depth+1, count + 1, arr);
					arr[i][j] = 0;
					arr[i+1][j] = tmp;
					arr[i-1][j] = tmp2;
				}
			}
		}
		

	}
	static boolean isPossible(int[][] arr) {
		for (int i = 1; i <= N; i++) {
			int height = 0;
			int current = i;
			while (++height <= H) {
				if (arr[current][height] == 1) {
					current++;
				} else if (arr[current-1][height] == 1) {
					current--;
				}
			}
			if (current != i) return false; 	
		}
		return true;
	}

}