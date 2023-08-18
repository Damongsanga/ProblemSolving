import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] s = new boolean[21];
	static int sum = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String function = st.nextToken();
			switch (function) {
			case "add":
				add(Integer.parseInt(st.nextToken()));
				break;
			case "remove":
				remove(Integer.parseInt(st.nextToken()));
				break;
			case "check":
				check(Integer.parseInt(st.nextToken()));
				break;
			case "toggle":
				toggle(Integer.parseInt(st.nextToken()));
				break;
			case "all":
				all();
				break;
			case "empty":
				empty();
			}
		}
		System.out.println(sb);

	}

	static void add(int x) {
		s[x] = true;
	}

	static void remove(int x) {
		s[x] = false;
	}

	static void check(int x) {
		sb.append(s[x] ? 1 : 0).append('\n');
	}

	static void toggle(int x) {
		s[x] = !s[x];
	}

	static void all() {
		for (int i = 1; i <= 20; i++) {
			s[i] = true;
		}

	}

	static void empty() {
		s = new boolean[21];
	}

}