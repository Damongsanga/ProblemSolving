import java.util.*;

class Solution {

    static class Node{
        int r; int c; int dir; int dist;
        public Node(int r, int c, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.dist = dist;
        }

    }

    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;
        int[][] map = new int[102][102];
        for (int[] rec : rectangle) {
            for (int i = rec[0]*2; i <= rec[2]*2; i++) {
                for (int j = rec[1]*2; j <= rec[3]*2; j++) {
                    if (map[i][j] != -1 && (i == rec[0]*2 || i == rec[2]*2 || j == rec[1]*2 || j == rec[3]*2)) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = -1;
                    }
                }
            }
        }


        Queue<Node> queue = new ArrayDeque();
        boolean[][] visited = new boolean[102][102];
        visited[characterX*2][characterY*2] = true;

        for (int i = 0; i < 4; i++) {
            int nr = characterX*2 + rr[i];
            int nc = characterY*2 + rc[i];
            if (nr < 0 || nc < 0 || nr >= 102 || nc >= 102) continue;
            if (map[nr][nc] != 1) continue;
            visited[nr][nc] = true;
            queue.add(new Node(nr, nc, i, 1));
        }

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.r == itemX*2 && now.c == itemY*2) {
                answer = Math.min(answer, now.dist);
                break;
            }

            for (int i = now.dir+1; i < now.dir+5; i++) {
                int ndir = i%4;
                int nr = now.r + rr[ndir];
                int nc = now.c + rc[ndir];
                if (nr < 0 || nc < 0 || nr >= 102 || nc >= 102) continue;
                if (map[nr][nc] != 1) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new Node(nr, nc, ndir, now.dist+1));
                break;
            }

        }


        return answer/2;
    }
}