import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                if (i > j) sum += dist[i][j]; // 중복 제거
            }
        }

        int answer = sum; // 모든 다리가 놓아져 있을 떄
        // i - j 다리 확인
        outer : for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (k == i || k == j)  continue;
                    if (dist[i][k] + dist[k][j] == dist[i][j]) { // 해당 거리 최소값이 바로 다리를 놓은 것이 아님을 확인한 경우 다리 삭제
                        answer -= dist[i][j];
                        break;
                    }
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        answer = -1;
                        break outer;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}