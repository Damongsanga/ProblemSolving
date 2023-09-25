import java.io.*;
import java.util.*;

class Enemy {
	int r;
	int c;
	int dist;

	Enemy(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}

public class Main {
	static int[] rr = { 0, -1, 0 };
	static int[] rc = { -1, 0, 1 };
	static int N;
	static int M;
	static int D;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		arr = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int answer = 0;
		// 궁수 위치 설정 (적을 내리지 않고 궁수를 위로 올리는 형태로 구현)
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					visited = new boolean[N][M]; // 적 삭제되었는지 확인하는 visited 배열
					int count = 0;
					for (int l = N; l > 0; l--) {
						int level = l; // 현재 궁수의 레벨
						Enemy a = bfs(level, i, level);
						Enemy b = bfs(level, j, level);
						Enemy c = bfs(level, k, level);
						// 삭제하지 않은 적인 경우 수를 count 해주고 삭제
						if (a != null && !visited[a.r][a.c]) {
							count++;
							visited[a.r][a.c] = true; // 적 삭제
						}
						if (b != null && !visited[b.r][b.c]) {
							count++;
							visited[b.r][b.c] = true;
						}
						if (c != null && !visited[c.r][c.c]) {
							count++;
							visited[c.r][c.c] = true;
						}

					}
					answer = Math.max(answer, count);
				}
			}
		}
		System.out.println(answer);
	}

	static Enemy bfs(int level, int bowman, int startlevel) {
		Queue<Enemy> queue = new ArrayDeque<>();
		queue.add(new Enemy(level, bowman, 0));

		while (!queue.isEmpty()) {

			Enemy now = queue.poll();
			int nowLevel = now.r;
			int col = now.c;
			int dist = now.dist;

			for (int m = 0; m < 3; m++) {
				int newLevel = nowLevel + rr[m];
				int newCol = col + rc[m];
				int newDist = dist + 1;
				// 시작 레벨에서는 옆으로 가지 말아야함
				if (newLevel == startlevel) continue;
				// 사정거리를 넘어서면 스킵
				if (newDist > D) continue;
				// 맵 범위를 벗어나면 스킵
				if (newLevel < 0 || newCol < 0 || newCol >= M) continue;
				Enemy next = new Enemy(newLevel, newCol, newDist);
				// 적이 죽지 않은 경우에만 해당 적을 화살로 맞추고 리턴
				if (arr[newLevel][newCol] == 1 && !visited[newLevel][newCol])
					return next;
				// 적이 아니거나 죽은 적인 경우 추가 탐색
				queue.add(next);
			}
		}
		return null;
	}

}