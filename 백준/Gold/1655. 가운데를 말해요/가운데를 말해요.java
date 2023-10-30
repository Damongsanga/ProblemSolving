
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();

		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				max.add(sc.nextInt());
			else
				min.add(sc.nextInt());
			if (!min.isEmpty() && max.peek() > min.peek()) {
				min.add(max.poll());
				max.add(min.poll());
			}
			sb.append(max.peek()).append("\n");

		}
		System.out.println(sb);

	}
}
