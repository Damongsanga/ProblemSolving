import java.io.*;
import java.util.*;

public class Main {

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

        int L = 2 * (max + sum) + 1; // 예를 들어 계산할 query의 max가 2인데 추가 14, 12이면 14 - 12 로 가능한데 max 만 계산한다면 문제가 발생할 수 있음으로 넉넉하게 dp 배열 생성
        int[] able = new int[L];
        able[L/2] = 1;

        for (int i = 1; i <= N; i++) {
            int c = chu[i];
            for (int j = 0; j < L; j++) {
                // 이번 턴에 갱신하는 경우는 탐색 방향에서 추가로 갱신하면 안된다 (이번 dp 탐색때 갱신된건 다음부터 추가 적용해야 함)
                if (0 < able[j] && able[j] <= i){
                    // 이미 잴 수 있는 경우는 갱신하면 탐색 방향에 따라 추가 갱신이 안될 수 있다
                    if (j + c < L && able[j+c] == 0) able[j+c] = i+1;
                    if (j - c >= 0 && able[j-c] == 0) able[j-c] = i+1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (able[query[i]+L/2] > 0) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb.toString().trim());

    }

}