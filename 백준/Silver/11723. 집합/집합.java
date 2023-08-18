import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		// 비트마스킹으로 풀이
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int s = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String function = st.nextToken();
			switch (function) {
			case "add":
				s = s | 1 << (Integer.parseInt(st.nextToken()) - 1);
				break;
			case "remove":
				s = s & (int) (Math.pow(2, 20) - Math.pow(2, (Integer.parseInt(st.nextToken()) - 1)) - 1);
				break;
			case "check":
				sb.append((s & 1 << (Integer.parseInt(st.nextToken()) - 1)) != 0 ? 1 : 0).append('\n');
				break;
			case "toggle":
				s = s ^ 1 << (Integer.parseInt(st.nextToken()) - 1);
				break;
			case "all":
				s = (int) Math.pow(2, 20) - 1;
				break;
			case "empty":
				s = 0;
			}
		}
		System.out.println(sb);

	}

	// add
	// 1111_0101을 add(2)인 경우
	// 0000_0010과 or 연산
	// 0000_0111 (2번째 자리 더해짐)

	// 1111_0101을 add(3)인 경우
	// 0000_0100과 or 연산
	// 0000_0101 (3번째 자리 더해질 것 없음)
	
	// remove
	// 1111_0101을 remove(3)인 경우
	// (2^20 - 1) - (2^2)
	// 1111_1111_1111_1111_1111 - 0000_0000_0000_0100
	// 1111_1111_1111_1111_1011 와 and 연산

	// 0000_0000_0000_1111_0101
	// 1111_1111_1111_1111_1011
	// 0000_0000_0000_1111_0001 (3번째 자리 삭제됨)

	// 1111_0101을 remove(2)인 경우
	// 0000_0000_0000_1111_0101
	// 1111_1111_1111_1111_1101
	// 0000_0000_0000_1111_0101 (2번째 자리 삭제될 것 없음)

	// check
	// 1111_0101 을 check(3)
	// 0000_0100 와 and 연산
	// 0000_0100 : 0 아님

	// 1111_0101 을 check(2)
	// 0000_0010 와 and 연산
	// 0000_0000 : 0 임

	// toggle
	// 1111_0101 을 toggle(3)
	// 0000_0100 와 xor 연산
	// 1111_0001

	// all
	// 1111_1111_1111_1111_1111로 세팅

	// empty
	// 0000_0000_0000_0000_0000으로 세팅

}