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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] arr = new int[M][N][H];
        Queue<Tomato> queue = new ArrayDeque<>();
        int unripped = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    arr[k][j][i] = Integer.parseInt(st.nextToken());
                    if (arr[k][j][i] == 0) unripped++;
                    if (arr[k][j][i] == 1) queue.add(new Tomato(k,j,i,0));
                }
            }
        }

        int answer = 0;

        if (unripped == 0){
            System.out.println(answer);
            System.exit(0);
        }

        while(!queue.isEmpty()){
            Tomato cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nr = cur.r + rr[i];
                int nc = cur.c + rc[i];
                int nh = cur.h + rh[i];
                if (nr < 0 || nc < 0 || nh < 0 || nr >= M || nc >=N || nh >= H) continue;
                if(arr[nr][nc][nh] != 0) continue;
                arr[nr][nc][nh] = 1;
                if (--unripped == 0) {
                    answer = cur.time+1;
                }
                queue.add(new Tomato(nr, nc, nh, cur.time+1));
            }
        }

        System.out.println(answer == 0 ? -1 : answer);

    }

}