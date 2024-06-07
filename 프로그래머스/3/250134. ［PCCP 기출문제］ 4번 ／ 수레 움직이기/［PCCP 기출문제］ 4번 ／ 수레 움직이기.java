import java.util.*;

/** 수레 움직이기
 *
* */
class Solution {
    static class Node{
        int r; int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
        public boolean equals(Node node){
            return this.r == node.r && this.c == node.c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int N;
    static int M;
    static Node bEnd;
    static Node rEnd;
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};
    static int answer = 987654321;
    static int[][] visited;

    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        visited = new int[N][M];

        Node bNow = null;
        Node rNow = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    rNow = new Node(i, j);
                    visited[i][j] = 1;
                } else if (maze[i][j] == 2) {
                    bNow = new Node(i, j);
                    visited[i][j] = 2;
                } else if (maze[i][j] == 3){
                    rEnd = new Node(i,j);
                } else if (maze[i][j] == 4){
                    bEnd = new Node(i,j);
                }
            }
        }

//        System.out.println("bNow = " + bNow);
//        System.out.println("rNow = " + rNow);
//        System.out.println("bEnd = " + bEnd);
//        System.out.println("rEnd = " + rEnd);
//        System.out.println();

        dfs(bNow, rNow, 0, maze);

        return answer == 987654321? 0 : answer;
    }


    private void dfs(Node bNow, Node rNow, int count, int[][] maze){

//        System.out.println("bNow = " + bNow);
//        System.out.println("rNow = " + rNow);
//        System.out.println("count = " + count);
//        System.out.println("answer = " + answer);
//        System.out.println();

        if (bNow.equals(bEnd) && rNow.equals(rEnd)) {
            answer = count;
            return;
        }

        if (count >= answer) return;

        for (int i = 0; i < 4; i++) {
            int bnr = bNow.r;
            int bnc = bNow.c;
            boolean bFinished = false;

            // 이동 여부 판단 및 이동
            if (bNow.equals(bEnd)) {
                bFinished = true;
                i+=4;
            } else {
                bnr += rr[i];
                bnc += rc[i];
            }

            // 이동할 수 없는 곳인 경우
            if (isNotMovable(bnr, bnc, maze)) continue;
            // 이동하였는데 그 곳이 방문한 곳인 경우
            if (!bFinished && (visited[bnr][bnc] == 2 || visited[bnr][bnc] == 3)) continue;


            for (int j = 0; j < 4; j++) {
                int rnr = rNow.r;
                int rnc = rNow.c;
                boolean rFinished = false;

                // 이동 여부 판단 및 이동
                if (rNow.equals(rEnd)){
                    rFinished = true;
                    j+=4;
                } else {
                    rnr += rr[j];
                    rnc += rc[j];
                }

                // 이동 할 수 없는 공간인 경우
                if (isNotMovable(rnr, rnc, maze)) continue;
                // 이동하였는데 그 곳이 방문한 곳인 경우
                if (!rFinished && (visited[rnr][rnc] == 1 || visited[rnr][rnc] == 3)) continue;

                // 서로 같은 곳으로 가는 경우
                if (bnr == rnr && bnc == rnc) continue;
                // 서로가 서로를 가로질러가는경우
                if (bnr == rNow.r && bnc == rNow.c && rnr == bNow.r && rnc == bNow.c) continue;

                // DFS
                visited[bnr][bnc]+=2;
                visited[rnr][rnc]+=1;
                dfs(new Node(bnr, bnc), new Node(rnr, rnc), count+1, maze);
                visited[bnr][bnc]-=2;
                visited[rnr][rnc]-=1;
            }
        }
    }

    private boolean isNotMovable(int nr, int nc, int[][] maze){
        return nr < 0 || nc < 0 || nr >= N || nc >= M || maze[nr][nc] == 5;
    }


}