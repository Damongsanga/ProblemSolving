import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int from, to, dist;

	public Node(int from, int to, int dist) {
		this.from = from;
		this.to = to;
		this.dist = dist;
	}
	
	public int compareTo(Node n) {
		return this.dist - n.dist;
	}

}

class Solution {

	static int[] parent;
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		parent[rootA] = rootB;
		return true;
		
	}

	public int solution(int n, int[][] costs) {
		int answer = 0;
		parent = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < costs.length; i++) {
			int[] data = costs[i];
			pq.add(new Node(data[0], data[1], data[2]));
		}
		
		int count = n-1;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (union(now.from, now.to)) {
				answer += now.dist;
				if (--count == 0) break;
			}
		}
		
		return answer;
	}
}