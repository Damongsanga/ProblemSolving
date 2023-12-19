import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Matrix {
        int r; int c;int val;

        public Matrix(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Matrix{" +
                    "r=" + r +
                    ", c=" + c +
                    ", val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Matrix[][] arr = new Matrix[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[i][i] = new Matrix(r, c, 0);
        }

        for (int gap = 1; gap < N; gap++) { // 간격
            for (int srt = 0; srt+gap < N; srt++) {
                int end = srt+gap;
                arr[srt][end] = new Matrix(arr[srt][end-1].r, arr[srt+1][end].c, Integer.MAX_VALUE);
                for (int mid = srt; mid < end; mid++) {
                    int tmp = arr[srt][mid].val + arr[mid+1][end].val + arr[srt][mid].r * arr[srt][mid].c * arr[mid+1][end].c;
                    arr[srt][end].val = Math.min(arr[srt][end].val, tmp);
                }
            }
        }

//        System.out.println(Arrays.deepToString(arr));

        System.out.println(arr[0][N-1].val);

    }
}