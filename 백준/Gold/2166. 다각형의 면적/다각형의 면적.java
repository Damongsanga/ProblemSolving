import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		long x; long y;
		Point(long x, long y){
			this.x = x;
			this.y = y;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Point[] arr = new Point[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		}

		long sum = 0;
		for (int i = 0; i < N-1; i++) {
			sum += arr[i].x * arr[i+1].y - arr[i].y * arr[i+1].x;
		}
		sum += arr[N-1].x * arr[0].y - arr[N-1].y * arr[0].x;
		double answer = Math.abs(sum) / 2.0;
		
		System.out.printf("%.1f", answer);
	}
	

}