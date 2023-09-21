import java.util.*;
import java.io.*;
 
class Solution {
	
	static class Node implements Comparable<Node>{
		int no, time;

		public Node(int no, int time) {
			this.no = no;
			this.time = time;
		}
		
		public int compareTo(Node o) {
			if (this.time == o.time) return this.no - o.no;
			return this.time - o.time;
		}

	}
     

    public static void main(String args[]) throws Exception {
 
        Scanner sc = new Scanner(System.in);
 
        for (int test_case = 1; test_case <= 10; test_case++) {
            int PN = 100;
        	int N = sc.nextInt();
            int srt = sc.nextInt();
            
            // 중복 제거를 위한 연결 set
            Set<Integer>[] edge = new Set[PN+1];
            
            for (int i = 1; i <= PN; i++) {
				edge[i] = new HashSet<>();
			}
            
            for (int i = 0; i < N/2; i++) {
            	int from = sc.nextInt();
            	int to = sc.nextInt();
				edge[from].add(to);
			}
            
            
            boolean[] visited = new boolean[PN+1];
            PriorityQueue<Node> pq = new PriorityQueue();
            pq.add(new Node(srt,0));
            visited[srt] = true;
            
            int curTime = 0;
        	int curNo = 0;
            
            while(!pq.isEmpty()) {
            	Node now = pq.poll();
            	curTime = now.time;
            	curNo = now.no;
            	
            	for (Integer next : edge[curNo]) {
            		if (visited[next]) continue;
            		visited[next] = true;
            		pq.add(new Node(next, curTime+1));
            	}
            }
             
            System.out.printf("#%d %d \n", test_case, curNo);
             
        }
    }
     

}