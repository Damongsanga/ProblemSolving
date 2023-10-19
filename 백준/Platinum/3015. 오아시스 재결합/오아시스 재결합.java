import java.io.*;
import java.util.*;

public class Main {

	static class People {
		long h;
		int count;

		public People(long h, int count) {
			this.h = h;
			this.count = count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<People> stack = new Stack();

		// stack 사용 이유 : 탐색 방향으로 더 큰게 나오면 서로를 볼 수가 없음, 더 볼 필요 없음으로 스택에서 뺌
		// 문제 : 키가 같은 경우를 어떻게 처리할껀가?

		long answer = 0;
		long stackPeople = 0;
		for (int i = 0; i < N; i++) {
			long tmp = Long.parseLong(br.readLine());
			while (!stack.isEmpty() && stack.peek().h < tmp) {
				int seeable = stack.pop().count;
				answer += seeable; // 지금 들어오는 녀석과 볼 수 있음, 이후 더이상 볼 수 없음으로 빼기
				stackPeople -= seeable;
			}

			if (!stack.isEmpty() && stack.peek().h == tmp) {
				answer += stack.peek().count++;
				if (stack.size() > 1) answer++;
				
			}
			else {
				if (!stack.isEmpty()) answer++;
				stack.push(new People(tmp, 1));
			}
			
			stackPeople++;
		}

		System.out.println(answer);
	}

}