import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long[] arr = new long[1_000_001];
        Arrays.fill(arr, 1);
        
        // 시간 복잡도 N * 
        for (int i = 2; i*i <= 1_000_000; i++) {
			for (int j = i*i; j <= 1_000_000; j += i) {
				arr[j] += i;
				if (i != j/i) {
					arr[j] += j/i;
				}
			}
		}
        
        for (int i = 2; i < arr.length; i++) {
        	arr[i] += i;
			arr[i] += arr[i-1];
		}
        
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
			sb.append(arr[Integer.parseInt(br.readLine())] + "\n");
		}
        System.out.println(sb);
        

    }


}