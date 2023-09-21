import java.io.*;
import java.util.*;

public class Main {
	static int[] rr = { -1, 1, 0, 0, 0, 0 };
	static int[] rc = { 0, 0, 1, -1, 0, 0 };
	static int[] rh = { 0, 0, 0, 0, -1, 1 };

	static class Node {
		int h, r, c, dist;

		public Node(int h, int r, int c, int dist) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

	}

	static int N = 5;
	static int[][][][] cube;
	static int[][][] checkCube;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1차원 : 판 종류
		// 2차원 : 회전 종류 (4개)
		// 3차원 : r
		// 4차원 : c
		cube = new int[N][4][N][N]; 
		
		// 회전하지 않은 N개의 판 인풋 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					cube[i][0][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		// 나머지 회전한 판들 계산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					cube[i][1][j][k] = cube[i][0][k][N - 1 - j];
					cube[i][2][j][k] = cube[i][0][N - 1 - j][N - 1 - k];
					cube[i][3][j][k] = cube[i][0][N - 1 - k][j];
				}
			}
		}

		checkCube = new int[N][N][N];
		
		int[] plates = new int[N];
		for (int i = 0; i < N; i++) {
			plates[i] = i;
		}
		
		permutation : do {
			
			roatation : for (int i = 0; i < (1 << (N * 2)); i++) {
				
				// 회전 세팅
				for (int j = 0; j < N; j++) {
					
					if ((i & (2 << (j*2))) > 0) {
						if ((i & (1 << (j*2))) > 0) {
							checkCube[j] = cube[plates[j]][3];
						} else {
							checkCube[j] = cube[plates[j]][1];
						}
					} else {
						if ((i & (1 << (j*2))) > 0) {
							checkCube[j] = cube[plates[j]][2];
						} else {
							checkCube[j] = cube[plates[j]][0];
						}
					}
					
					
					// 입구가 막혀있으면 탐색하지 않음
					if (j == 0 && checkCube[0][0][0] == 0) {
						continue roatation;
					}
					
					// 출구가 막혀있으면 탐색하지 않음
					if (j == N-1 && checkCube[N-1][N-1][N-1] == 0)
						continue roatation;
				}

				// BFS
				Queue<Node> queue = new ArrayDeque<>();
				queue.add(new Node(0, 0, 0, 0));
				boolean[][][] visited = new boolean[N][N][N];
				visited[0][0][0] = true;
				
				while (!queue.isEmpty()) {
					Node now = queue.poll();
					int h = now.h;
					int r = now.r;
					int c = now.c;
					int dist = now.dist;
//					System.out.println("h, r, c, dist: " + h +" "+ r +" "+ c + " " + dist);
					
					if (dist > answer) {
						continue roatation;
					}
					
					if (h == N-1 && r == N-1 && c== N-1 && dist < answer) {
						answer = dist;
//						System.out.println("answer : " + answer);
						continue roatation;
					}
					
					for (int j = 0; j < 6; j++) {
						int nh = h + rh[j];
						int nr = r + rr[j];
						int nc = c + rc[j];
						
						if (nh < 0 || nr < 0 || nc < 0 || nh >=N || nr >= N || nc >=N) continue;
						if (!visited[nh][nr][nc] && checkCube[nh][nr][nc] == 1) {
							visited[nh][nr][nc] = true;
							queue.add(new Node(nh,nr,nc,dist+1));
						}
					}
				}
			}
		} while(nextPermutation(plates));

		System.out.println(answer == Integer.MAX_VALUE? -1 : answer);
	}
	
	static boolean nextPermutation(int[] p) {
		int len = p.length;
		int i = len-1;
		while(i>0 && p[i-1] >=p[i]) --i;
		if (i == 0) return false;
		
		int j = N-1;
		while (p[i-1] >= p[j]) j--;
		
		swap(p,i-1,j);
		
		int k = N-1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	static void swap (int[] p, int a, int b) {
		int tmp = p[a];
		p[a] = p[b];
		p[b] = tmp;
	}
	
}