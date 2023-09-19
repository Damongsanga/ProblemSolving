import java.io.*;
import java.util.*;

public class Main {

    // 위상정렬 & dp 풀이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] buildTime = new int[N+1]; // 건물 건설시간 저장하는 배열
            int[] dp = new int[N+1]; 
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                buildTime[j] = Integer.parseInt(st.nextToken());
                dp[j] = buildTime[j]; // dp 초기화
            }

            // 인접리스트
            ArrayList<Integer>[] arr = new ArrayList[N+1];
            for (int j = 1; j <= N; j++) {
                arr[j] = new ArrayList<>();
            }
            // 접근 배열
            int[] inV = new int[N+1];
            
            
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                inV[b]++;
            }

            int target = Integer.parseInt(br.readLine());

            // 위상정렬
            Queue<Integer> queue = new ArrayDeque<>();
            // 초기에 접근 행렬이 0인 (우선순위가 가장 먼저인) 값을 큐에 넣음 
            for (int j = 1; j <= N; j++) {
                if (inV[j] == 0) queue.add(j);
            }

            int answer = 0;
            while(!queue.isEmpty()){
                int currnt  = queue.poll(); // 현재 건물 번호
                int time = dp[currnt]; // 지금까지 소요된 시간을 dp에서 가져옴
                
                // 타겟 도착시 탈출 (forEach 문이 아닌 queue에서 poll 해줄 때 확인해야 모든 경우를 고려한 것임)
                if (currnt == target){
                    answer = time;
                    break;
                }

                // 다음 건물의 dp 값을 지금까지의 dp 값 + 다음 건물 건설시간보다 크면 갱신
                for (Integer tmp : arr[currnt]){
                    dp[tmp] = Math.max(buildTime[tmp]+time, dp[tmp]);
                    inV[tmp]--; // 접근 행렬 1 감소 
                    if (inV[tmp] == 0) queue.add(tmp); // 접근 행렬 0일시 큐에 넣음
                }

            }

            System.out.println(answer);

        }

    }
}