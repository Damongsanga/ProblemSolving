import java.io.*;
import java.util.*;

public class Main {

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[8];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 8; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,new int[8], arr, new boolean[8]);
		System.out.println(answer);
		
	}
	
	static void dfs(int depth, int[] selected, int[] arr, boolean[] visited) {
		if (depth == 8) {
			for(int i = 0; i < 8; i++) {
				int a = i;
				int b = (i+1)%8;
				int c = (i+2)%8;
				if(!CCW(arr[selected[a]], arr[selected[b]], arr[selected[c]])) {
					return;
				}
			}
			answer++;
		}
		for (int i = 0; i < 8; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			selected[depth] = i;
			dfs(depth+1, selected, arr, visited);
			visited[i] = false;
		}
	}
	
	
	static boolean CCW(int a, int b, int c) {
		// (y-c) = -(c/a) * (x-a)
		// y = x 의 교점이 b,b 보다 안쪽인지 바깥쪽인지
		
		return a*c * Math.sqrt(2) <= b * (a+c);		

	}
	

}