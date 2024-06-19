import java.util.*;

/** 블록 이동하기
 */

/** 1. 일단 회전 구현,
 *  2. 방문 배열을 3차원으로 구성해야 하는가? 가로, 세로, 현재 놓아져 있는 위치
 *      놓아진 위치를 어떻게..? 무조건 작은 것 기준으로 & 가로인지 세로인지만 보면 될듯!
 *  3. 로봇의 위치를 어떻게 표현할 것인가? => 가로, 세로 좌표의 합은 무조건 1만큼 차이가 날 것임으로 작은거, 큰거 이렇게 2개로 나누면 되지 않을까?
 *  4.
 *
 * */
class Solution {
    static int[] rr = {0,1,0,-1};
    static int[] rc = {1,0,-1,0};
    static int R;
    static int C;
    static int[][][] visited;

    // r, c는 로봇이 차지하는 2개의 칸 중 입구에 가까운 칸이며, dir는 0, 1만 가진다
    static class Robot{
        int r; int c; int dir; int count;
        Robot(int r, int c, int dir, int count){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }

    }

    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        visited = new int[R][C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0,0,0, 0));
        visited[0][0][0] = 0;

        while(!queue.isEmpty()){
            Robot robot = queue.poll();

            int r = robot.r;
            int c = robot.c;
            int dir = robot.dir;

            if ((r + rr[dir] == R-1 && c + rc[dir] == C-1) || (r == R-1 && c == C-1)) return robot.count;

            for (int i = 0; i < 4; i++) {
                move(board, queue, new Robot(r + rr[i], c + rc[i], dir, robot.count));
            }

            if (dir == 0){
                if (r-1 >= 0 && board[r-1][c+1] == 0)
                    move(board, queue, new Robot(r-1, c, 1, robot.count));
                if (r-1 >= 0 && board[r-1][c] == 0)
                    move(board, queue, new Robot(r-1, c+1, 1, robot.count));
                if (r+1 < R && board[r+1][c+1] == 0)
                    move(board, queue, new Robot(r, c, 1, robot.count));
                if (r+1 < R && board[r+1][c] == 0)
                    move(board, queue, new Robot(r, c+1, 1, robot.count));
            } else {
                if (c-1 >= 0 && board[r+1][c-1] == 0)
                    move(board, queue, new Robot(r, c-1, 0, robot.count));
                if (c-1 >= 0 && board[r][c-1] == 0)
                    move(board, queue, new Robot(r+1, c-1, 0, robot.count));
                if (c+1 < C && board[r+1][c+1] == 0)
                    move(board, queue, new Robot(r, c, 0, robot.count));
                if (c+1 < C && board[r][c+1] == 0)
                    move(board, queue, new Robot(r+1, c, 0, robot.count));
            }
        }

        return -1;
    }


    private static void move(int[][] board, Queue<Robot> queue, Robot robot) {
        int nr1 = robot.r;
        int nc1 = robot.c;
        int nr2 = robot.r + rr[robot.dir];
        int nc2 = robot.c + rc[robot.dir];
        if (!(nr1 >= 0 && nc1 >= 0 && nr2 < R && nc2 < C)) return;
        if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) return;
        if (visited[nr1][nc1][robot.dir] <= robot.count) return;
        visited[nr1][nc1][robot.dir] = robot.count;
        queue.add(new Robot(nr1, nc1, robot.dir, robot.count+1));
    }

}