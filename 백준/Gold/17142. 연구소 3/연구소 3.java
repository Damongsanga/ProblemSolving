import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int r; int c; int time;
	Node(int r, int c, int time){
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class Main {
	static int[][] arr;
	static List<Node> list;
	static int[] rr = {-1,0,1,0};
	static int[] rc = {0,1,0,-1};
	static int answer = 987654321;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        
        // 인풋 입력
        arr = new int[N][N];
        list = new ArrayList<>(); // 바이러스 위치 저장
        int zeroCount = 0; // 빈 공간 갯수 카운트
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Node(i, j, 0));
				}
				if (arr[i][j] == 0) zeroCount++; 
			}
		}      
        
        backtracking(0, 0, N, M, zeroCount, new Node[M], new boolean[list.size()]);
        System.out.println(answer == 987654321 ? -1 : answer); // 한 번도 답 갱신 안됐으면 -1 출력
        

    }
    public static void backtracking(int depth, int idx, int N, int M, int zeroCount, Node[] virusarr, boolean[] isChecked) {
    	
    	if (depth == M) {
    		bfs(virusarr, N, M, zeroCount);
    		return;
    	}
    	
    	for (int i = idx; i < list.size(); i++) {
    		if (!isChecked[i]) {
    			isChecked[i] = true; 
        		virusarr[depth] = list.get(i); // 바이러스 선택
    			backtracking(depth+1, i+1, N, M, zeroCount, virusarr, isChecked);
    			isChecked[i] = false;
    		}
		}
    }
    public static void bfs(Node[] virusarr, int N, int M, int zeroCount) { 	
    	
    	boolean[][] visited = new boolean[N][N];
    	int time = 0;
    	int zeroCountBfs = 0; // bfs 탐색 횟수 카운트
    	
    	// queue에 활성화된 바이러스 옮김
    	Queue<Node> queue = new ArrayDeque<>();
    	for (int i = 0; i < M; i++) {
			queue.add(virusarr[i]);
			visited[virusarr[i].r][virusarr[i].c] = true;
		}
    	
    	// bfs
    	while (!queue.isEmpty()) {
    		Node n = queue.poll();
    		time = n.time;
    		if (arr[n.r][n.c] == 0) {
    			zeroCountBfs++;
    		}
    		
    		
    		for (int i = 0; i < 4; i++) {
				int r = n.r + rr[i];
				int c = n.c + rc[i];
				if (r < 0 || c < 0 || r >= N || c >= N ) continue;
				if (visited[r][c]) continue;
				if (arr[r][c] == 1) continue;
				
				visited[r][c] = true;
				queue.add(new Node(r, c, n.time+1));
			}
    		
    		// 바이러스가 전부 감염되었는지 확인하여 성립하면 답을 갱신 (bfs 탐색 횟수가 빈공간 수랑 같은지 확인)
    		if (zeroCountBfs == zeroCount) {
    			answer = Math.min(answer, time);
    			break;
    		}
    	}
    }
}