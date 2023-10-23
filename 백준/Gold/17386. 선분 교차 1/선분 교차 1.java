import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] p1 = new long[4];
		for(int i = 0; i < 4; i++) {
			p1[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		long[] p2 = new long[4];
		for(int i = 0; i < 4; i++) {
			p2[i] = Long.parseLong(st.nextToken());
		}
		
		int answer = 0;
		int a = ccw(p1[0], p1[1], p1[2], p1[3], p2[0], p2[1]);
		int b = ccw(p1[0], p1[1], p1[2], p1[3], p2[2], p2[3]);
		int c = ccw(p2[0], p2[1], p2[2], p2[3], p1[0], p1[1]);
		int d = ccw(p2[0], p2[1], p2[2], p2[3], p1[2], p1[3]);
		
		if (a * b <= 0 && c * d <= 0) answer = 1;
		System.out.println(answer);
		
	}
	static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		return (x2 - x1) * (y3 - y2) - (x3 - x2) * (y2 - y1) > 0 ? 1 : -1;
	}

}