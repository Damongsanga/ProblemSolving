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

    static class Robot{
        Node back; int dir; int count;
        Robot(int r, int c, int dir, int count){
            this.back = new Node(r, c);
            this.dir = dir;
            this.count = count;
        }

        static class Node{
            int r; int c;
            Node(int r, int c) {
                this.r = r;
                this.c = c;
            }
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

            int r = robot.back.r;
            int c = robot.back.c;
            int dir = robot.dir;

            if (r + rr[dir] == R-1 && c + rc[dir] == C-1) return robot.count;
            if (r == R-1 && c == C-1) return robot.count;


            for (int i = 0; i < 4; i++) {
                move(board, queue, new Robot(r + rr[i], c + rc[i], dir, robot.count));
            }

            if (dir == 0 && r+rr[1] < R && board[r+rr[1]][c+rc[1]] == 0){
                move(board, queue, new Robot(r + rr[dir], c + rc[dir], 1, robot.count));
            } else if (dir == 1 && c+rc[0] < C && board[r+rr[0]][c+rc[0]] == 0) {
                move(board, queue, new Robot(r + rr[dir], c + rc[dir], 0, robot.count));
            }
        }

        return -1;
    }



    private static void move(int[][] board, Queue<Robot> queue, Robot robot) {
        int nr1 = Math.min(robot.back.r, robot.back.r + rr[robot.dir]);
        int nc1 = Math.min(robot.back.c, robot.back.c + rc[robot.dir]);
        int nr2 = Math.max(robot.back.r, robot.back.r + rr[robot.dir]);
        int nc2 = Math.max(robot.back.c, robot.back.c + rc[robot.dir]);
        if (!(nr1 >= 0 && nr2 >= 0 && nc1 >= 0 && nc2 >= 0 && nr1 < R && nr2 < R && nc1 < C && nc2 < C)) return;
        if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) return;
        if (visited[nr1][nc1][robot.dir % 2] <= robot.count) return;
        visited[nr1][nc1][robot.dir % 2] = robot.count;
        queue.add(new Robot(nr1, nc1, robot.dir % 2, robot.count+1));
    }

}