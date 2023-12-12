import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        int r; int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static class Room {
        int val; int no;

        public Room(int val, int no) {
            this.val = val;
            this.no = no;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "val=" + val +
                    ", no=" + no +
                    '}';
        }
    }

    static int[] rr = {0,-1,0,1};
    static int[] rc = {-1,0,1,0};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[M][N];
        Room[][] rooms = new Room[M][N];

        int count = 0;
        int max = 0;
        int no = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                count++;
                no++;
                max = Math.max(max, bfs(i, j, no, map, visited, rooms));
            }
        }

        int maxWallRemoved = max;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (j != N-1 && rooms[i][j].no != rooms[i][j+1].no)
                    maxWallRemoved = Math.max(maxWallRemoved, rooms[i][j].val + rooms[i][j+1].val);
                if (i != M-1 && rooms[i][j].no != rooms[i+1][j].no)
                    maxWallRemoved = Math.max(maxWallRemoved, rooms[i][j].val + rooms[i+1][j].val);
            }
        }

        System.out.println(count + "\n" + max + "\n" + maxWallRemoved);

    }

    static int bfs(int r, int c, int no, int[][] map, boolean[][] visited, Room[][] rooms){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c));
        visited[r][c] = true;

        int returnVal = 1;
        List<Node> list = new ArrayList<>();

        while(!q.isEmpty()){
            Node now = q.poll();
            list.add(now);
            for (int i = 0; i < 4; i++) {
                int nr = now.r + rr[i];
                int nc = now.c + rc[i];
                if (nr < 0 || nc < 0 || nr >= M || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if ((map[now.r][now.c] & (1<<i)) > 0) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc));
                returnVal++;
            }
        }

        for (Node node : list){
            rooms[node.r][node.c] = new Room(returnVal, no);
        }

        return returnVal;
    }


}