import java.util.*;

/** 석유 시추
 * 풀었던 문제여서 가볍게 복습만 함
 * 알고리즘 : 플러드필
 * 핵심 아이디어 :
 * 만약 석유관에 대해 계산할 때마다 석유 덩어리 사이즈를 구하면 효율성 테스트 통과 못함.
 * 미리 전체 land에 대해 석유 덩어리들마다 번호를 매겨 각각 사이즈를 기록해두고
 * 석유관이 특정 번호 석유 덩어리들을 통과할 때 그 크기를 바로 가져와야함
 * 여기서 주의할 점은 중복계산되지 않도록 해야함. (set을 사용)
 * */

class Solution {
    static int N;
    static int M;
    static int[] rr = {1,0,-1,0};
    static int[] rc = {0,-1,0,1};
    static int answer = Integer.MAX_VALUE;
    static int[][] visited;
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        int nowR = 0;
        int nowC = 0;
        outer : for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = board[i].charAt(j);
                if (c == 'R') {
                    nowR = i;
                    nowC = j;
                    break outer;
                }
            }
        }

        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[nowR][nowC] = 0;
        
        dfs(nowR, nowC, 0, board);

        return answer == Integer.MAX_VALUE? -1 : answer;
    }

    private static void dfs(int nowR, int nowC, int count, String[] board){
        if (count >= answer) return;

        if (board[nowR].charAt(nowC) == 'G') {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int moveLength = move(nowR, nowC, i, board);
            if (moveLength == 0) continue;

            int newR = nowR + rr[i] * moveLength;
            int newC = nowC + rc[i] * moveLength;
            if (visited[newR][newC] <= count) continue;
            visited[newR][newC] = count;
            dfs(newR, newC, count + 1, board);
        }
    }

    private static int move(int nowR, int nowC, int direction, String[] board){
        int count = 0;
        while(isMovable(nowR+rr[direction], nowC+rc[direction], board)){
            nowR += rr[direction];
            nowC += rc[direction];
            count++;
        }
        return count;
    }

    private static boolean isMovable(int nowR, int nowC, String[] board){
        return !(nowR < 0 || nowC < 0 || nowR >= N || nowC >= M || board[nowR].charAt(nowC) == 'D');
    }
}