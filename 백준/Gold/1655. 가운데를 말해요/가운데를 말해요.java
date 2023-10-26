import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 작은 값들을 담을 maxpq
		PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
		// 큰 값들을 담을 minpq
		PriorityQueue<Integer> minpq = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		// 첫번째 값 넣기
		maxpq.add(Integer.parseInt(br.readLine()));
		sb.append(maxpq.peek() + "\n");		
		
		// 지금까지 쌓인 숫자 갯수가 홀수냐 짝수냐로 분기
		// 짝수일 경우 앞쪽의 수를 구하라고 했기 때문에 maxpq에서 peek 한 값을 출력해야
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