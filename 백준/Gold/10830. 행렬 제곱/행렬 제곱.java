import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static int[][][] dp;
    static int[] dpSub;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long B = sc.nextLong();
        int[][] arr = new int[N][N];
        int[][] ans = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt() % 1000;
                if (i==j) ans[i][j] = 1;
            }
        }

        int count = Long.toBinaryString(B).length();

        if (count == 1) {
            ans = arr;

        } else {
            dp = new int[count+1][N][N];
            dpSub = new int[count+1];
            dp[1] = arr;
            // DP 배열
            for (int i = 2; i < count+1; i++) {
                dp[i] = mul(dp[i-1], dp[i-1], N);
            }

            recur(B);
            // DP 사용 횟수
            for (int i = 0; i <= count; i++) {
                if (dpSub[i] > 0) {
                    ans = mul(ans, dp[i], N);
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static int[][] mul(int[][] a, int[][] b, int N) {
        int[][] res = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
                res[i][j] %= 1000;
            }
        }

        return res;
    }

    static void recur(long n){

        int dpIdx = Long.toBinaryString(n).length();
        long tmp = (long) 1 << (dpIdx-1);

        if (n == tmp) dpSub[dpIdx]++;
        else {
            dpSub[dpIdx]++;
            recur(n - tmp);
        }

    }

}