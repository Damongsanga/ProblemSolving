import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Process implements Comparable<Process>{
        int m; int t;

        @Override
        public int compareTo(Process o) {
            return this.t - o.t;
        }

        @Override
        public String toString() {
            return "Process{" +
                    "m=" + m +
                    ", t=" + t +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Process[] processes = new Process[N];
        st = new StringTokenizer(br.readLine());
        int maxM = 0;
        for (int i = 0; i < N; i++) {
            processes[i] = new Process();
            processes[i].m = Integer.parseInt(st.nextToken());
            maxM += processes[i].m;
        }
        int maxT = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            processes[i].t = Integer.parseInt(st.nextToken());
            maxT += processes[i].t;
        }

        int[] dp = new int[maxT+1];
        Arrays.sort(processes);

        for (int i = 0; i < N; i++) {
            for (int j = maxT; j >= processes[i].t; j--) {
                int t = processes[i].t;
                int m = processes[i].m;
                dp[j] = Math.max(dp[j - t] + m, dp[j]);
            }
        }

        for (int i = 0; i <= maxT; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}