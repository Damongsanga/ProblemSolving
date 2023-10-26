import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minpq = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		
		// 초기화 1번째
		maxpq.add(Integer.parseInt(br.readLine()));
		sb.append(maxpq.peek() + "\n");		
		
		for (int i = 1; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (maxpq.size() == minpq.size()) {
				if (n > minpq.peek()) {
					maxpq.add(minpq.poll());
					minpq.add(n);
				} else {
					maxpq.add(n);
				}
			} else {
				if (n >= maxpq.peek()) {
					minpq.add(n);
				} else {
					minpq.add(maxpq.poll());
					maxpq.add(n);
				}
			}
			sb.append(maxpq.peek() + "\n");
		}
		
		System.out.println(sb);
	}

}