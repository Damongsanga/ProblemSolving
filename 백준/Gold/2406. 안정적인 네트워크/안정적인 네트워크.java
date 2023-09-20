import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	static int N;
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
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] linked = new int[M][2];
		int[][] costs = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			linked[i][0] = Integer.parseInt(st.nextToken());
			linked[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		// 이미 연결된 간선 가격 0으로 설정
		for (int i = 0; i < M; i++) {
			costs[linked[i][0]][linked[i][1]] = 0;
		}	
		
		// 간선 리스트
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if (i == j) continue;
				edges.add(new Edge(i,j, costs[i][j]));
			}
		}
		
		Collections.sort(edges);
		int size = N-1;
		int X = 0;
		int K = 0;
		ArrayList<Integer[]> result = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			Edge edge = edges.get(i);
			if (union(edge.from, edge.to)) {
				size--;
			}
		}
		
		if (size <=1) {
			System.out.println(X + " " + K);
			return;
		}
		
		for (int i = M; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if (union(edge.from, edge.to)) {
				size--;
				X += costs[edge.from][edge.to];
				K++;
				result.add(new Integer[] {edge.from, edge.to});
			}
			
			if (size <= 1) {
				System.out.println(X + " " + K);
				StringBuilder sb = new StringBuilder();
				for (Integer[] integers : result) {
					sb.append(integers[0] + " " + integers[1] + "\n");
				}
				System.out.println(sb);
				return;
			}
		}
	}
}
