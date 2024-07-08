import java.util.*;

/** 모두 0으로 만들기
 * 핵심 아이디어
 * 1. 위상정렬 : 인접하는 노드가 1개인 노드가 리프노드. 리프노드가 a 값을 가지고 있으면 무조건 절대값(a)만큼 주어진 행동을 해야 한다.
 * 2. 따라서 리프노드부터 차례대로 부모노드(인접노드)에게 자신의 값을 넘겨주면 된다.
 * 헤멘 부분
 * 1. 인접행렬을 1 감소하는 부분을 queue에서 뽑아올 때 해야한다.
 * 2. 주변 노드들을 탐색하면서 인접행렬이 "1"일때 가져온다.
 * 3. int 범위를 넘어갈 수 있다... 답이랑 배열 모두 long으로 해야함
 *
 * */

class Solution {
    static int N;
    static int M;
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        int[][] extendedKey = new int[M + N * 2 - 2][M + N * 2 - 2];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                extendedKey[i + (N-1)][j + (N-1)] = key[i][j];
            }
        }

        if (isFilledLock(lock)) return true;

        for (int i = 0; i < 4; i++) {
            for (int startR = 0; startR < N+M-2; startR++) {
                for (int startC = 0; startC < N+M-2; startC++) {
                    if (isUnlocked(startR, startC, extendedKey, lock)) return true;
                }
            }
            if (i != 3) lock = rotate(lock);
        }
        return false;
    }

    private boolean isFilledLock(int[][] lock){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) return false;
            }
        }
        return true;
    }

    private boolean isUnlocked(int startR, int startC, int[][] extendedKey, int[][] lock) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (lock[r][c] == extendedKey[startR + r][startC + c]) return false;
            }
        }
        return true;
    }

    // 시계 반대방향으로 90도 회전
    static int[][] rotate(int[][] lock){
        int[][] newLock = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i][j] = lock[N-j-1][i];
            }
        }
        return newLock;
    }
}

