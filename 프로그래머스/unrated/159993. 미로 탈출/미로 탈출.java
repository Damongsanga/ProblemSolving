import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	public class Node {
		int x;
		int y;
		int dist;

		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static int[] rx = {-1,0,1,0};
	static int[] ry = {0,1,0,-1};

	public int solution(String[] maps) {
		int answer_door = 987654321;
		int answer_lever = 987654321;
		int N = maps.length;
		int M = maps[0].length();
		boolean[][] visited = new boolean[N][M];
		int[][] arr = new int[N][M];
		int srt_x = 0;
		int srt_y = 0;
		int lever_x = 0;
		int lever_y = 0;
		int end_x = 0;
		int end_y = 0;
		Queue<Node> queue = new ArrayDeque<Node>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				switch (maps[i].toCharArray()[j]) {
				case ('S'):
					srt_x = i;
					srt_y = j;
					break;
				case ('L'):
					lever_x = i;
					lever_y = j;
					break;
				case ('E'):
					end_x = i;
					end_y = j;
					break;
				case ('X'):
					arr[i][j] = -1;
				
				}
			}
		}
		
		queue.add(new Node(srt_x, srt_y, 0));
		visited[srt_x][srt_y] = true;
		
		while (!queue.isEmpty()) {
			Node nd = queue.poll();
			
			if (nd.dist > answer_lever) continue;
			
			if (nd.x == lever_x && nd.y == lever_y) {
				lever_x = nd.x;
				lever_y = nd.y;
				answer_lever = Math.min(answer_lever, nd.dist);
			}
			
			for (int i = 0; i < 4; i++) {
				int new_x = nd.x + rx[i];
				int new_y = nd.y + ry[i];
				if (new_x < 0 || new_y < 0 || new_x >= N || new_y >= M) continue;
				if (!visited[new_x][new_y] && arr[new_x][new_y] != -1) {
					visited[new_x][new_y] = true;
					queue.add(new Node(new_x, new_y, nd.dist+1));
				}
			}
		}
		
		if (answer_lever == 987654321) return -1;
		
		queue = new ArrayDeque<Node>();
		visited = new boolean[N][M];
		queue.add(new Node(lever_x, lever_y, answer_lever));
		visited[lever_x][lever_y] = true;
		
		while (!queue.isEmpty()) {
			Node nd = queue.poll();
			
			if (nd.dist > answer_door) continue;
			
			if (nd.x == end_x && nd.y == end_y) {
				lever_x = nd.x;
				lever_y = nd.y;
				answer_door = Math.min(answer_door, nd.dist);
			}
			
			for (int i = 0; i < 4; i++) {
				int new_x = nd.x + rx[i];
				int new_y = nd.y + ry[i];
				if (new_x < 0 || new_y < 0 || new_x >= N || new_y >= M) continue;
				if (!visited[new_x][new_y] && arr[new_x][new_y] != -1) {
					visited[new_x][new_y] = true;
					queue.add(new Node(new_x, new_y, nd.dist+1));
				}
			}
		}

		return answer_door == 987654321 ? -1 : answer_door;
	}
}