import java.io.*;
import java.util.*;



public class Main {
    static class Node implements Comparable<Node>{
        int r, c, dist, bit, keys;
        Node(int r, int c, int dist, int bit, int keys){
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.bit = bit;
            this.keys = keys;
        }
        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }

    static int answer = Integer.MAX_VALUE;
    static int[] rr = {1,0,-1,0};
    static int[] rc = {0,1,0,-1};
    static char[][] arr;

    // 키를 가지고 있는지 여부를 비트마스킹으로 판단..!!!
    // 최단거리 구하는 문제임으로 BFS로 접근?
    // visited 처리를 int로 하여 먹은 열쇠의 종류수에 따라 돌아갈 수 있는 횟수를 고려하면 될 듯!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] arr = new char[N][M];

        int srt_r = 0;
        int srt_c = 0;
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp.charAt(j);
                if (arr[i][j] == '0') {
                    srt_r = i; srt_c = j;
                }
            }
        }
        
        Queue<Node> pq = new ArrayDeque<>();
        boolean[][][] visited = new boolean[1<<6][N][M];
        visited[0][srt_r][srt_c] = true;
        pq.add(new Node(srt_r, srt_c, 0, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int r= now.r; int c = now.c; int dist = now.dist;int bit = now.bit; int keys = now.keys;
            
            if (arr[r][c] == '1'){
                answer = dist;
                break;
            }

            if ('a' <= arr[r][c] && arr[r][c] <= 'f' && (bit & (1<<(arr[r][c] - 'a'))) == 0){
                bit = bit | (1<<(arr[r][c] - 'a'));
                keys++;
            }

            for(int i = 0; i < 4; i++){
                int nr = r + rr[i];
                int nc = c + rc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (arr[nr][nc] == '#') continue;
                if (visited[bit][nr][nc]) continue;
                if ('A' <= arr[nr][nc] && arr[nr][nc] <= 'F'){
                    if ((bit & (1 << (arr[nr][nc] - 'A'))) == 0) {
                        continue;
                    }
                }
                visited[bit][nr][nc] = true;
                pq.add(new Node(nr, nc, dist+1, bit, keys));
            }

        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }


}