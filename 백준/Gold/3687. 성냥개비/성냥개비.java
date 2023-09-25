import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String[] dp = new String[101];
		dp[2] = "1"; dp[3] = "7"; dp[4] = "4"; dp[5] = "2"; dp[6] = "6"; dp[7] = "8"; dp[8] = "10"; 
		for (int i = 9; i <= 100; i++) {
			String a = dp[i-7] + "8";
			String b = dp[i-6].substring(0, 1) + "0" + dp[i-6].substring(1);
			
			int idx = dp[i-5].length();
			for (int j = 1; j < dp[i-5].length(); j++) {
				if (dp[i-5].charAt(j) > '2') {
					idx = j;
				}
			}
			String c = dp[i-5].substring(0, idx) + "2" + dp[i-5].substring(idx);
			dp[i] = minString(a, minString(b, c));
		}		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder max = new StringBuilder();
			
			// max;
			if (N%2 == 1) max.append("7");
			else max.append("1");
			for (int j = 1; j < N/2; j++) {
				max.append("1");
			}
			
			String min = dp[N];
			sb.append(min + " " + max + "\n");
		}
		
		System.out.println(sb);

	}
	
	static String minString(String a, String b) {
		if (a.length() > b.length()) return b;
		if (a.length() < b.length()) return a;
		
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) > b.charAt(i)) return b;
			else if (a.charAt(i) < b.charAt(i)) return a;
		}
		return a;
	}
}