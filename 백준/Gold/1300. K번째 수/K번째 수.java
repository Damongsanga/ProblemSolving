import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 반복문
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long lo = 1;
        long hi = K;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (func(mid) < K) {
                lo = mid + 1;
            } else {
                hi = mid;
            }

        }

        System.out.print(lo);

    }

    public static long func(long val){
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, val/i);
        }
        return count;
    }

}