import java.io.*;
import java.util.*;

public class Main {

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int totalsum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			totalsum += arr[i];
		}
		ArrayList<Integer>[] edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			for (int j = 0; j < f; j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		answer = totalsum;
		boolean[] chosen = new boolean[N + 1];
		chosen[1] = true;
		Combination(2, totalsum, chosen, N, edges, arr);

		System.out.println(answer == totalsum ? -1 : answer);

	}

	static void Combination(int depth, int totalsum, boolean[] chosen, int N, ArrayList<Integer>[] edges, int[] arr) {

		if (depth == N + 1) {
			boolean[] visited = new boolean[N + 1];
			int A = BFSCHECK(chosen, true, visited, edges, arr);
			int B = BFSCHECK(chosen, false, visited, edges, arr);
			if (A != -1 && B != -1 && A + B == totalsum && Math.abs(A - B) < answer)
				answer = Math.abs(A - B);
			return;
		}

		chosen[depth] = true;
		Combination(depth + 1, totalsum, chosen, N, edges, arr);
		chosen[depth] = false;
		Combination(depth + 1, totalsum, chosen, N, edges, arr);

	}

	static int BFSCHECK(boolean[] chosen, boolean flag, boolean[] visited, ArrayList<Integer>[] edges, int[] arr) {

		int sum = 0;
		Queue<Integer> queue = new ArrayDeque<>();

		int now = -1;
		for (int i = 1; i < chosen.length; i++) {
			if (chosen[i] == flag) {
				now = i;
				break;
			}
		}
		if (now == -1)
			return -1;

		queue.add(now);
		visited[now] = true;

		while (!queue.isEmpty()) {
			int n = queue.poll();
			sum += arr[n];
			for (Integer i : edges[n]) {
				if (visited[i])
					continue;
				if (chosen[i] != flag)
					continue;
				visited[i] = true;
				queue.add(i);
			}
		}

		return sum;

	}

}
