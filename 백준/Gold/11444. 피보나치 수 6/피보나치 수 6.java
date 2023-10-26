import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007;

	// K1 = 1, K2 = 2로 표현할 때 
	// (1, 1)
	// (1, 0)
	// (Kn, Kn-1)
	// (Kn-1, Kn-2)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long N = Long.parseLong(br.readLine());
		System.out.println(recur(N)[1][0]); // Kn-1을 구해야
		
	}
	static long[][] recur(long n) {
		if (n == 1) return new long[][] {{1,1},{1,0}};
		
		long[][] tmp = recur(n/2);
		long[][] tmp2 = new long[2][2];
		tmp2[0][0] = (tmp[0][0] * tmp[0][0] + tmp[0][1] * tmp[1][0]) % MOD;
		tmp2[0][1] = (tmp[0][0] * tmp[1][0] + tmp[0][1] * tmp[1][1]) % MOD; 
		tmp2[1][0] = (tmp[1][0] * tmp[0][0] + tmp[1][1] * tmp[1][0]) % MOD;
		tmp2[1][1] = (tmp[1][0] * tmp[1][0] + tmp[1][1] * tmp[1][1]) % MOD;
		
		if (n % 2 == 1) {
			tmp[0][0] = (tmp2[0][0] + tmp2[0][1]) % MOD; // Kn => Kn + kn-1
			tmp[0][1] = tmp2[0][0]; // Kn-1 => Kn-1 + Kn-2
			tmp[1][0] = tmp2[0][0]; // Kn-1 => Kn
			tmp[1][1] = tmp2[1][0]; // Kn-2 => Kn-1
			return tmp;
		}
		
		return tmp2;
		
	}
}