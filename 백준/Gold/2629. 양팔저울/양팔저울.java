import java.io.*;
import java.util.*;

public class Main {

    static int[] rr = {0,0,1,-1,0,0};
    static int[] rc = {1,-1,0,0,0,0};
    static int[] rh = {0,0,0,0,1,-1};

    static class Tomato{
        int r; int c; int h; int time;
        Tomato(int r, int c, int h, int time){
            this.r = r;
            this.c = c;
            this.h = h;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] chu = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
            sum += chu[i];
        }
        int M = Integer.parseInt(br.readLine());
        int[] query = new int[M];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            query[i] = Integer.parseInt(st.nextToken());
            max = Math.max(query[i], max);
        }

        int L = 2 * (max + sum) + 1;
        int[] able = new int[L];
        able[L/2] = 1;

        for (int i = 1; i <= N; i++) {
            int c = chu[i];
            for (int j = 0; j < L; j++) {
                if (0 < able[j] && able[j] <= i){
                    if (j + c < L && able[j+c] == 0) able[j+c] = i+1;
                    if (j - c >= 0 && able[j-c] == 0) able[j-c] = i+1;
                }
            }
        }

//        System.out.println(Arrays.toString(able));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (able[query[i]+L/2] > 0) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb.toString().trim());

    }

}