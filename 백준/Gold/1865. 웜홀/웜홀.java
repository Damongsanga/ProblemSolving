import java.io.*;
import java.util.*;

public class Main {
	static class Edge{
		int to, dist;
		Edge(int to, int dist){
			this.to = to;
			this.dist = dist;
		}
	}
	
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			ArrayList<Edge>[] edge = new ArrayList[N+1];

			for (int i = 1; i <= N; i++) {
				edge[i] = new ArrayList();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				edge[from].add(new Edge(to,d)); // 양방향
				edge[to].add(new Edge(from,d)); // 양방향
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				edge[from].add(new Edge(to,-d)); // 단방향
			}

			boolean flag = false;
			for (int i = 1; i <= N; i++) {
				if (BF(i, edge, N, M, W)) {
					flag = true;
					sb.append("YES\n");
					break;
				}
			}
			
			if (!flag) sb.append("NO\n");
		}
		
		System.out.println(sb);
		
	}
	static boolean BF(int srt, ArrayList<Edge>[] edge, int N, int M, int W) {
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dist[srt] = 0;
		boolean updated = false;
		// 정점의 개수 -1 보다 1개 (즉, 정점 개수만큼 돈다)
		for (int i = 0; i < N; i++) {
			updated = false;
			// 전체 정점에 대하여 모든 간선을 구한다
			for (int j = 1; j <= N; j++) {
				for (Edge e : edge[j]) {
					if (dist[j] != INF && dist[e.to] > dist[j] + e.dist) {
						dist[e.to] = dist[j] + e.dist;
						updated = true;
					}
				}
			}
			if (!updated) break;
		}
		
		// 만약 정점의 개수만큼 돌았는데도 update가 되었다는 것은 사이클이 존재한다는 것
		return updated? true : false; 
	}
}
