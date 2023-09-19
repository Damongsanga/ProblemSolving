import java.io.*;
import java.util.*;

class Node {
    int r;
    int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[] rr = {-1, 0, 1, 0};
    static int[] rc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer[]>[] arr = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Integer[] {b, c});
        }

        int[] answer = new int[N];

        // pq는 높은 레벨 순서대로
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(((o1, o2) -> o2[0] - o1[0]));
        pq.offer(new Integer[] {N,1});
        int level;
        int count = 0;
        while (!pq.isEmpty()){
            Integer[] now = pq.poll();
            level = now[0];
            count += now[1];
            // 모아서 아래 레벨 넣기
            if (!pq.isEmpty() && now[0] == pq.peek()[0]){
                continue;
            } else {
                if (arr[level].size() == 0) {
                    answer[level] += count;
                } else {
                    for (Integer[] tmp: arr[level]) {
                        pq.offer(new Integer[] {tmp[0], tmp[1] * count});
                    }
                }
                count = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            if (answer[i] > 0){
                sb.append(i + " " + answer[i] + "\n");
            }
        }

        System.out.println(sb);


    }


}