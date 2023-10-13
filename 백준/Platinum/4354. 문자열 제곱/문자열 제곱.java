import java.io.*;
import java.util.*;

public class Main {

	static int answer;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while(sc.hasNext()) {
			String str = sc.next();
			char[] s = str.toCharArray();
			if (s[0] == '.') System.exit(0);
			int N = s.length;
			int[] pi = new int[N];
			int j = 0;
			for (int i = 1; i < N; i++) {
				while(j > 0 && s[i] != s[j]) {
					j = pi[j-1];
				}
				
				if (s[i] == s[j]) {
					j++;
				}
				pi[i] = j;
			}

			int max = N-pi[N-1];
			if (!str.substring(0, max).equals(str.substring(N-max, N))) max = N;
			
			System.out.println(N/max);
		}
		
		
		
	}

}
