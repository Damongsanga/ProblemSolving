import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] rr = {-1,-1,-1,0,1,1,1,0,0};
    static int[] rc = {1,0,-1,-1,-1,0,1,1,0};
    static int N = 8;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][][] arr = new char[N][N][N];
        for (int r = 0; r < N; r++) {
            String tmp = br.readLine();
            for (int c = 0; c < N; c++) {
                arr[0][r][c] = tmp.charAt(c);
            }
        }

        for (int i = 1; i < N; i++) {
            Arrays.fill(arr[i][0], '.');
            for (int r = 1; r < N; r++) {
                arr[i][r] = Arrays.copyOf(arr[i-1][r-1], N);
            }
        }

        boolean[][][] visited = new boolean[N][N][N];
        DFS(0,N-1,0,arr,visited);
        System.out.println(answer);

    }

    static void DFS(int count, int r, int c, char[][][] arr, boolean[][][] visited){
        if (answer == 1) return;

        if (count == N) {
            answer = 1;
            return;
        }

        for (int i = 0; i < 9; i++) {
            int nr = r + rr[i];
            int nc = c + rc[i];
            if (nr < 0 || nc < 0 || nr>= N || nc >= N) continue;
            if (visited[count][nr][nc] || (count+1 < N && visited[count+1][nr][nc])) continue;
            if (arr[count][nr][nc] == '#' || (count+1 < N && arr[count+1][nr][nc] == '#')) continue;
            visited[count][nr][nc] = true;
            DFS(count+1, nr, nc, arr, visited);
            visited[count][nr][nc] = false;
        }
    }
}