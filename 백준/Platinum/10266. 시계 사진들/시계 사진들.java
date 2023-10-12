import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] clockOrigin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            clockOrigin[i] = Integer.parseInt(st.nextToken());
        }
        int[] pOrigin = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pOrigin[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(clockOrigin);
        int[] clock = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == N-1) clock[i] = clockOrigin[0] + 360000 -clockOrigin[i];
            else clock[i] = clockOrigin[i+1]-clockOrigin[i];
        }

        Arrays.sort(pOrigin);
        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == N-1) p[i] = pOrigin[0] + 360000 -pOrigin[i];
            else p[i] = pOrigin[i+1]-pOrigin[i];
        }

        int[] pi = new int[N];

        int j = 0;
        for (int i = 1; i < N; i++) {
            while(j > 0 && p[i] != p[j]){
                j = pi[j-1];
            }
            if (p[i] == p[j]) j++;
            pi[i] = j;
        }


        j = 0;
        for (int i = 0; i < N*2-1; i++) {
            int idx = i;
            if (i >= N) idx = i - N;

            while(j > 0 && clock[idx] != p[j]){
                j = pi[j-1];
            }

            if (p[j] == clock[idx]){
                if (j == N-1){
                    System.out.println("possible");
                    System.exit(0);
                } else {
                    j++;
                }
            }
        }

        System.out.println("impossible");

    }
}