import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int K;
	static int visited[] = new int[100001]; // 방문 배열
	
	// bfs로 풀이. 해당 위치에 도착하는데 걸리는 시간을 기록하는 방문 배열을 만들어 풀이하자.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		
		N = Integer.valueOf(inputs[0]);
		K = Integer.valueOf(inputs[1]);
		
		int result = bfs(N);
		System.out.println(result);
	}

	private static int bfs(int node){
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(node);
		int index = node;
		int n = 0;
		visited[index] = 1;

		while (queue.isEmpty() == false) {
			n = queue.remove();
			
			if (n == K){
				return visited[n]-1;
			}
			// 한칸 전으로 이동
			if (n-1>=0 && visited[n-1] == 0){
				visited[n-1] = visited[n]+1;
				queue.add(n-1);
			}
			// 한칸 뒤로 이동
			if (n+1 <= 100000 && visited[n+1] == 0){
				visited[n+1] = visited[n]+1;
				queue.add(n+1);
			}
			// 2배 뒤로 이동
			if (2*n <= 100000 && visited[2*n] == 0){
				visited[2*n] = visited[n] + 1;
				queue.add(2*n);
			}
		}

		return -1;
	}
}