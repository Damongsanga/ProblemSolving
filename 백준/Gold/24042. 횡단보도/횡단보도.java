import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int from, to; 
	long dist;
	public Edge(int from, int to, long dist) {
		this.from = from;
		this.to = to;
		this.dist = dist;
	}
	

	public int compareTo(Edge o) {
		return (int) (this.dist - o.dist);
	}
	
	
}

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		boolean[] visited = new boolean[N+1];
		
		ArrayList<Edge>[] arr = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(new Edge(from, to, i));
			arr[to].add(new Edge(to, from, i));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 초기값 설정
		long time = 0;
		pq.add(new Edge(1,1,0));
		dist[1] = 0;
		
		// 지금 시간보다 짧은 길이가 들어오면 M 추가하기
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			// 우선순위에서 뺄 때 방문처리
			visited[now.to] = true;
			time = dist[now.to];
			
			ArrayList<Edge> tmp = arr[now.to];
			for (int i = 0; i < tmp.size(); i++) {
				
				Edge next = tmp.get(i);
				if (visited[next.to]) continue;
				
				if (time > next.dist) {
					next.dist += ((time - next.dist) / M + 1) * M;
				}
				
				if (next.dist < dist[next.to]) {
					dist[next.to] = next.dist;
//					System.out.println(Arrays.toString(dist));
//					System.out.println("next to " + next.to);
//					System.out.println(dist[next.to]);
//					System.out.println("time : " + time);
//					System.out.println();
					
					if (next.to == N) break;
					
					
					pq.add(next);
				}
			}

		}
		
		System.out.println(dist[N]+1);
		

	}
}