import java.util.*;

/** 사라지는 발판
 * 1학기때 가장 어려웠던 문제로 결국 못풀어서 다시 풀어봄
 * 혼자서 완전히 풀지는 못했고 풀이 도움 보고 품
 * 알고리즘 : 백트랙킹
 * 시간 복잡도 : O(4^(M+N)) : 상하좌우 최악의 케이스
 * 핵심 아이디어 : 
 * 최선의 방향으로 간다는 것은, 이동할 수 있는 방향 (최대 3가지)에 대한 성공/실패 여부를 기반으로 판단
 * 1. 만약 해당 방향 어떤 것으로도 가도 진다면 최대한 길게 버티도록
 * 2. 어떤 방향으로 가더라도 이긴다면 최대한 빠르게 이기도록
 * 3. "어떤 방향으로 가면 이기고 어떤 방향으로 가면 진다면" 이기는 방향으로 가야함. 2번과 같은 로직
 * 주의 사항 : 
 * 만약 두 유저가 같은 위치에 있었다면 현재 차례의 유저가 움직일 수 있으면 승리 with step+1, 못움직이면 패배 with step
 * recursion 함수에서 다음 recursion함수의 결과값은 "상대방"의 결과임 -> win값이 true라면 상대방이 이겼음으로 내가 진 것임!
 *
 *
* */
class Solution {

    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};
    static int R;
    static int C;
    static int[][] arr;
    static boolean[][] visited;

    private static class Result {
        boolean win;
        int steps;
        Result(boolean win, int steps){
            this.win = win;
            this.steps = steps;
        }
    }


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        arr = board;
        visited = new boolean[R][C];
        return rec(aloc, bloc, 0).steps;
    }

    private Result rec(int[] aloc, int[] bloc, int steps){
        int[] loc = steps % 2 == 0? aloc : bloc;
        int winMin = Integer.MAX_VALUE;
        int loseMax = Integer.MIN_VALUE;
        boolean isOpponentWinner = true;
        boolean canMove = false;

        for (int i = 0; i < 4; i++) {
            int nr = loc[0] + rr[i];
            int nc = loc[1] + rc[i];
            
            // 이동 여부 판단
            if (!isValidPos(nr, nc)) continue;
            canMove = true;

            // 같은 블록에 두 유저가 존재하는데 이동이 가능하면 step이 1개 추가되고 승리한다.
            if (aloc[0] == bloc[0] && aloc[1] == bloc[1])
                return new Result(true, steps+1);

            visited[loc[0]][loc[1]] = true;
            // 다음 경우의 Result 경우는 상대방의 경우임으로 opponentResult가 win이라면 "내" 결과는 lose이다.
            Result opponentResult = steps%2 == 0? rec(new int[]{nr, nc}, bloc, steps+1) : rec(aloc, new int[]{nr, nc}, steps+1);
            visited[loc[0]][loc[1]] = false;

            // (중요로직) 상대방이 모두 이기는 경우만 내가 반드시 진다고 판단해야 한다.
            isOpponentWinner &= opponentResult.win; 
            
            // 상대방이 이기는 경우 (내가 지는 경우) 가장 오래 버티도록 최대값을 구한다.
            if (opponentResult.win)
                loseMax = Math.max(loseMax, opponentResult.steps);
            // 상대방이 지는 경우 (내가 이기는 경우) 가장 빨리 끝내도록 최소값을 구한다.
            else
                winMin = Math.min(winMin, opponentResult.steps);
        }

        // 모든 경우에 대해 이동 못하면 바로 패배
        if (!canMove)
            return new Result(false, steps);

        // 적이 무조건 이기는 경우
        if (isOpponentWinner){
            return new Result(false, loseMax);
        }
        // 이외
        return new Result(true, winMin);

    }

    private boolean isValidPos(int r, int c){
        return (0 <= r && r < R) && (0<= c && c <C) && !visited[r][c] && arr[r][c] == 1;
    }


}