import java.io.*;
import java.util.*;



/*
 *
 * 1. 왜 dist[] 배열을 long으로 선언 해야 하나요?(int로 하면 57%, long으로 하면 70%대에서 틀립니다) 언더플로우의 범위는 N * M * W 로 20억임으로 안날 것이라고 생각이 되는데..
 *   다익스트라, 플로이드워셜, 벨만포드에서 INF 값을 설정하는 것이 어렵습니다 ㅠㅠ
 * 2. 아래 코드에서 도저히 고민해도 왜 틀렸는지 모르겠네요.. 뭔가 DFS로 역추적하는데서 틀린 것 같은데, 한 번만 확인 부탁드립니다 ! ㅠㅠ
 *
 * */


public class Main {
    static final int NINF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] edge = new int[N+1][N+1];

        // 역추적을 위해 인접배열로 구성 (N이 최대 100 임으로 가능)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                edge[i][j] = NINF;
            }
        }

        // 양수 사이클을 찾는 것으로 풀어도 되나 기존 벨만포드 알고리즘처럼 풀기 위해 인풋의 부호를 바꾸었습니다.
        // 돈을 최대한 많이 얻는 것이 유리 : 최단거리를 구하는 알고리즘임으로 돈을 얻는 것을 음수, 돈을 뺏기는 것을 양수로 표현
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            edge[from][to] = dist; // 부등호 반대로!
        }

        // 벨만 포드 알고리즘
        long[] dist = new long[N+1];
        Arrays.fill(dist, NINF);

        dist[1] = 0;
        boolean updated = false;
        for (int i = 0; i < N-1; i++) { // 정점 - 1 개수만큼 탐색
            updated = false;
            // 전체 간선 탐색
            for (int j = 1; j <= N; j++) { // now
                if (dist[j] == NINF) continue;
                for (int k = 1; k <= N; k++){ // next
                    if (edge[j][k] == NINF) continue;
                    if (dist[k] < dist[j] + edge[j][k]){
                        dist[k] = dist[j] + edge[j][k];
                        updated = true;
                    }
                }
            }
            if (!updated) break;
        }

        ArrayList<Integer> routeList = new ArrayList<>();
        for (int j = 1; j <= N; j++) { // now
            if (dist[j] == NINF) continue;
            for (int k = 1; k <= N; k++){ // next
                if (edge[j][k] == NINF) continue;
                if (dist[k] < dist[j] + edge[j][k]){
                    dist[k] = dist[j] + edge[j][k];
                    routeList.add(k);
                }
            }
        }

        // 사이클 존재하거나 목표지점까지 도착하지 못하는 경우 (최적 경로가 존재하지 않는 경우)
        if(dist[N] == NINF || BFS(routeList, edge, N)){
            System.out.println(-1);
        }
        // 사이클이 없고 목표지점까지 도착할 수 있는 경우 (최적 경로가 존재하는 경우)
        else{
            boolean[] visited = new boolean[N+1];
            ArrayList<Integer> tmpList = new ArrayList<>();
            visited[N] = true;
            tmpList.add(N);
            DFS(N, tmpList, visited, N, dist, edge);
        }
    }

    // 도착점에서부터 역추적하여 최단 거리 경로를 찾기
    static void DFS(int now, ArrayList<Integer> list, boolean[] visited, int N, long[] dist, int[][] edge){
        // 도착점에 도착한다면 답 출력 후 종료
        if (now == 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = list.size()-1; i >=0 ; i--) {
                sb.append(list.get(i) + " ");
            }
            System.out.println(sb);
            System.exit(0); // 바로 프로그램 종료
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue; // 거리가 0인 경우 무한하게 돌 수 있음으로 방문확인 필요, 사이클 존재하지 않는 경우임으로 중복해서 돌아오는 경우 존재하지 않음
            if (dist[now] == dist[i] + edge[i][now]){ // 과거 정점까지의 dist 값과 간선 거리 값이 현재 정점의 dist 값과 같다면 최단거리 경로
                visited[i] = true;
                list.add(i);
                DFS(i, list, visited, N, dist, edge);
                list.remove(list.size()-1);
            }
        }
    }

    static boolean BFS(ArrayList<Integer> routeList, int[][] edge, int N){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        for (Integer i : routeList){
            queue.add(i);
            visited[i] = true;
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            if (now == 1 || now == N) return true;

            for (int i = 1; i <= N; i++) {
                int next = edge[now][i];
                if (next == NINF) continue;
                if (visited[i]) continue;
                visited[i] = true;
                queue.add(i);
             }
        }
        return false;
    }

}