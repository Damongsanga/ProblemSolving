import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	double cost;
		
	public Edge(int from, int to, double cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	public int compareTo(Edge o) {
		return Double.compare(this.cost, o.cost);
	}
	
	
}

class Solution {
	
	static int[] parents;
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		parents[rootA] = rootB;
		return true;
	}
	
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			parents = new int[N+1];
			long[][] dist = new long[N+1][2];
			
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= N; j++) {
					dist[j][i] = sc.nextInt();
				}
			}
			double E = sc.nextDouble();
			
			// 간선배열
			Edge[] edges = new Edge[N*(N-1)/2];
			
			// 간선 정의
			int idx = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = i+1; j <= N; j++) {
					double distance = (Math.pow(dist[i][0] - dist[j][0], 2) + Math.pow(dist[i][1] - dist[j][1], 2));
					distance *= E;
					edges[idx++] = new Edge(i, j, distance);
				}
			}
			// cost 오름차순 정렬
			Arrays.sort(edges);
			
			//makeset
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			
			// 간선을 N-1개만큼 선택할때까지 비용을 추가
			// 만약 사이클이 형성되면 (union시 이미 같은 집합이라면) 스킵
			double result = 0;
			int count = 0;
			for (Edge edge : edges) {
				if (union(edge.from, edge.to)) {
					result += edge.cost;
					if (++count == N-1) break;
				}
			}
			
			// 반올림
			long output = Math.round(result);

			System.out.println("#"+test_case + " " +output);
			
		}
	}
	
	
}