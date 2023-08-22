import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Node implements Comparable<Node> {
	int r;
	int c;
	int size;

	Node(int r, int c, int size) {
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public int compareTo(Node o) {
		if (this.r == o.r) {
			return this.c - o.c;
		}
		return this.r - o.r;
	}
	
	public boolean equals(Node o) {
		if (this.r == o.r && this.c == o.c) return true;
		return false;
	}
}


public class Main {
	static int[] rr = { -1, 0, 1, 0 };
	static int[] rc = { 0, 1, 0, -1 };
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] arr = new int[N][N];

		// 인풋
		Node babyShark = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = sc.nextInt();
				if (size == 9) {
					babyShark = new Node(i, j, 2);
				} else if (size != 0) {
					arr[i][j] = size;
				}
			}
		}

		// 물고기 이동 및 성장
		int answer = 0;
		int babycount = 0;

		int eaten = 0;
		while (true) {
			// 다음 물고기 선정
			int dist = bfs(babyShark, arr);
			if (dist == 987654321)
				break; // 먹을 물고기가 없다는 뜻임으로 탈출해야

			answer += dist;
		
			// 아기 상어 성장
			if (babyShark.size == 7) continue;
			if (++babycount == babyShark.size) {
				babyShark.size++;
				babycount = 0;
			}
		}

		System.out.println(answer);

	}


	static int bfs(Node babyShark, int[][] arr) {
		int dist = 1;
		int size = babyShark.size;
		boolean[][] visited = new boolean[N][N];
		Queue<Node> queue = new ArrayDeque<Node>();
		PriorityQueue<Node> pq = new PriorityQueue<>();

		queue.add(new Node(babyShark.r, babyShark.c, dist));
		visited[babyShark.r][babyShark.c] = true;

		
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			
			if (current.size!= dist) {
				if (!pq.isEmpty()) {
					// 다음 물고기 위치로 이동 & 먹기
					Node nextFish = pq.peek();
					babyShark.r = nextFish.r;
					babyShark.c = nextFish.c;
					arr[nextFish.r][nextFish.c] = 0;
					// 거리 return
					return dist;
				} else {
					dist=current.size;
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int r = current.r + rr[i];
				int c = current.c + rc[i];
				if (r < 0 || c < 0 || r >= N || c >= N)
					continue;
				if (arr[r][c] > size)
					continue;
				if (!visited[r][c]) {
					visited[r][c] = true; 
					queue.add(new Node(r, c, current.size + 1));
					if (0 < arr[r][c] && arr[r][c] < size)
						pq.add(new Node(r, c, current.size + 1));
				}
			}
		}

		return 987654321;
	}

}