
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][] value = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		value[0][0] = map[0][0];

		for (int i = 1; i < M; i++) {
			value[0][i] = value[0][i - 1] + map[0][i];
		}

		for (int i = 1; i < N; i++) {
			value[i][0] = value[i - 1][0] + map[i][0];

			for (int j = 1; j < M; j++) {
				value[i][j] = Math.max(value[i - 1][j], value[i][j - 1]) + map[i][j];
			}

			int temp = value[i - 1][M - 1] + map[i][M - 1];

			value[i][M - 1] = Math.max(value[i][M - 1], temp);

			for (int j = M - 2; j >= 0; j--) {
				temp = Math.max(temp, value[i - 1][j]) + map[i][j];
				value[i][j] = Math.max(temp, value[i][j]);
			}

		}
		System.out.println(value[N - 1][M - 1]);

	}
}
