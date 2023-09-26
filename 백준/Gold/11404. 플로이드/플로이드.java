import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		final int MAX = 100_000 * N + 1;
		
		// 초기값 설정 1 : 자기 자신을 제외한 값을 무한대 값으로 설정 (간선 0개 거치는 경우)
		// 여기서 Integer.MAX_VALUE 넣으면 오버플로우 뜰 수 있음!!
		// 모든 간선의 합보다 큰 값으로 설정
		int[][] dist = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				dist[i][j] = MAX;
			}
		}
		
		// 초기값 설정 2 : 간선 정보 입력 (간선 1개 거치는 경우)
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			// 단방향 간선
			// 문제에서 동일한 경로에 여러개의 노선이 있을 수 있음으로 최소 비용의 노선만 인풋으로 받음
			if (dist[from][to] != 0) dist[from][to] = Math.min(dist[from][to], distance);
			else dist[from][to] = distance; 
		}
				
		// 플로이드 워셜 (간선 2 ~ N-1개 거치는 경우)
		// 거쳐가는 중간 노드를 가운데에 넣어야함
		for (int i = 1; i <= N; i++) { // 거쳐가는 갯수!!!
			for (int j = 1; j <= N; j++) { // 시작
				for (int k = 1; k <= N; k++) { // 끝
					dist[j][k] = Math.min(dist[j][i] + dist[i][k], dist[j][k]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append((dist[i][j] == MAX ? 0 : dist[i][j] )+ " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}
	
}