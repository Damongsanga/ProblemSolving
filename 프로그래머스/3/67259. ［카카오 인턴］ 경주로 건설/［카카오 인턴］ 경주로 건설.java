

import java.util.*;

class Solution {
	
	static class Node{
		int r; int c; int dir; int cost;
		public Node(int r, int c, int dir, int cost) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", dir=" + dir + ", cost=" + cost + "]";
		}
		
		
	}
	
	static int[] rr = {-1,0,1,0};
	static int[] rc = {0,1,0,-1};
	
    public int solution(int[][] board) {
    	int N = board.length;
    	int INF = 987654321;
    	int[][][] dp = new int[4][N][N];
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < 4; j++) {
    			Arrays.fill(dp[j][i], INF);
			}
		}
    	
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        pq.add(new Node(0, 0, 1, 0));
        pq.add(new Node(0, 0, 2, 0));
        dp[1][0][0] = 0;
        dp[2][0][0] = 0;
        
        int answer = INF;
        while(!pq.isEmpty()) {
        	Node now = pq.poll();
        	
        	if (now.r == N-1 && now.c == N-1) { 
        		answer = Math.min(answer, now.cost);
        		continue;
        	}
        	
        	for (int i = 0; i < 4; i++) {
				int r = now.r + rr[i];
				int c = now.c + rc[i];
				if (r < 0 || c < 0 || r >= N || c >= N) continue;
				if (board[r][c] == 1) continue;
				if (now.dir == i) {
					if (dp[i][r][c] >= now.cost+100) {
						dp[i][r][c] = now.cost+100;
						pq.add(new Node(r,c,i, now.cost+100));
					}
				} else {
					if (dp[i][r][c] >= now.cost+600) {
						dp[i][r][c] = now.cost+600;
						pq.add(new Node(r,c,i, now.cost+600));
					}
				}
			}
        }
        
        return answer;
    }
}