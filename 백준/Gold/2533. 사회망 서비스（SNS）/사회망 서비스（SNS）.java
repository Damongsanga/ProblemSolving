import java.io.*;
import java.util.*;


public class Main {
	static ArrayList<Integer>[] edge;
	static boolean[] visited;
	static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        edge = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
        
        for (int i = 0; i < N-1; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	edge[a].add(b);
        	edge[b].add(a);
		}
        
        visited = new boolean[N+1];
        dp = new int[N+1][2];
        
        DFS(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    } 
    
    static void DFS(int node) {
    	visited[node] = true;
    	dp[node][1] = 1;
    	dp[node][0] = 0;
    	for (Integer child : edge[node]) {
    		if (visited[child]) continue;
    		DFS(child);
    		dp[node][0] += dp[child][1];
    		dp[node][1] += Math.min(dp[child][0], dp[child][1]);
    	}
    }

}