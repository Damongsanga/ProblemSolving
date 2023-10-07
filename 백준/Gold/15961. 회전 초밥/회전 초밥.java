import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] count = new int[d + 1];
        int[] sushi = new int[N + k - 1];
        boolean isThereCoupon = false;
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            if (!isThereCoupon && sushi[i] == c) isThereCoupon = true;
        }
        for (int i = N; i < N + k - 1; i++) {
            sushi[i] = sushi[i - N];
        }

        count[c]++;
        int sum = 1;
        int answer = 1;

        for (int l = 0, r = 0; r < N+k-1; r++) {
            if (++count[sushi[r]] == 1) sum++;
            if (r - l == k) {
                if (--count[sushi[l++]] == 0) sum--;
            }
            answer = Math.max(sum, answer);
        }
        
        System.out.println(answer);
    }

}