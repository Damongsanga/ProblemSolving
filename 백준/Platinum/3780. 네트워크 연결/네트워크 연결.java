import java.io.*;
import java.util.*;

public class Main {
	
	static void union(int a, int b) {

		parent[a] = b; // 부모 갱신
		dist[a] = Math.abs(a-b) % 1000; // 거리 갱신
	}
	
	
	static int findwithdist(int a) {
		if (parent[a] == a) return a;
		int p = findwithdist(parent[a]);
		dist[a] += dist[parent[a]];
		parent[a] = p;
		// 내려보냄
		return parent[a];
	}
	
	static int[] parent;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			parent = new int[N+1];
			dist = new int[N+1];
			for (int i = 1; i < N+1; i++) {
				parent[i] = i;
			}
			
			
			outer : while (true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				switch (s) {
				case "O" : 
					break outer;
				case "I" :
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					union(a,b);
					
					break;
				case "E" :
					int c = Integer.parseInt(st.nextToken());
					findwithdist(c);
					System.out.println(dist[c]);
				}
			}
		}
		
	}
}