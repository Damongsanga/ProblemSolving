import java.util.*;

public class Main {
	static int answer = 0;
	static int N;
	static int K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		int[] bitmask = new int[N]; // 인풋을 비트마스킹하여 저장할 배열 (단어비트)
		
		int totalbit = 0; // 전제 단어들에서 사용된 글자들을 저장할 전체비트
		totalbit = (totalbit | (1 << 'a'-'a'));
		totalbit = (totalbit | (1 << 'c'-'a'));
		totalbit = (totalbit | (1 << 'i'-'a'));
		totalbit = (totalbit | (1 << 'n'-'a'));
		totalbit = (totalbit | (1 << 't'-'a'));
		int totalbitfinal = 't'-'a'; // 사용된 글자들 중 마지막 글자
		int initbit = totalbit;
		
		for (int i = 0; i < N; i++) {
			int bittmp = initbit;
			String tmp = sc.next();
			for (int j = 4; j < tmp.length()-4; j++) {
				int idx = tmp.charAt(j) - 'a';
				// 단어의 글자들을 하나씩 탐색하면서 단어비트와 전체 비트를 갱신
				bittmp = (bittmp | (1 << idx));
				totalbit = (totalbit | (1 << idx));
				totalbitfinal = Math.max(idx, totalbitfinal); // 사용된 글자들 중 마지막 글자 갱신
			}
			bitmask[i] = bittmp;
		}
		
		if (K < 5) 
			System.out.println(answer);
		else if (K == 26)
			System.out.println(N);
		else {
			backtracking(0, 0, initbit, bitmask, totalbit, totalbitfinal);
			System.out.println(answer);
		}
		
	}
	
	
	public static void backtracking(int depth, int idx, int chosenbit, int[] bitmask, int totalbit, int totalbitfinal) {
		
//		System.out.println(depth + " " + (idx) + " " + chosenbit);
		
		if (depth == K-5) { // 전부 탐색했거나 글자 K개를 모두 고르면 그 K개 안에 표현될 수 있는 단어 수 카운팅
			int count = 0;
			for (int i = 0; i < N; i++) {
				// 단어비트와 K개 고른 글자비트를 & 연산해서 단어비트가 나오면 K개 단어로 모두 표현될 수 있는 것이다.
				if((bitmask[i] & chosenbit) == bitmask[i]) {
					count++;
				}
			}
			answer = Math.max(count, answer);
			return;
		}
		
		for (int i = idx; i < 26; i++) {
			if ((chosenbit & (1<<i)) > 0) continue;
//			if ((totalbit & (1<<i)) > 0) {
				backtracking(depth+1, i+1, (chosenbit | (1<<i)), bitmask, totalbit, totalbitfinal); // 고른 경우
//			}
		}
	}
}
