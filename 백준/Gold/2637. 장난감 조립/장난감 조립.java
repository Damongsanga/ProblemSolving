import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        // 장난감 조립 정보를 저장할 인접리스트
        ArrayList<Integer[]>[] arr = new ArrayList[N+1];
        int[] inV = new int[N+1];
        int[] answer = new int[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Integer[] {b, c});
            inV[b]++;
        }

        Queue<Integer> queue = new ArrayDeque();
        queue.add(N);
        answer[N] = 1;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for (Integer[] tmp : arr[now]){
                int toy = tmp[0];
                int num = tmp[1];
                answer[toy] += answer[now] * num;
                if(--inV[toy] == 0) queue.add(toy);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (arr[i].size() == 0)
                sb.append(i + " " + answer[i] + "\n");
        }
        System.out.println(sb);

    }
}