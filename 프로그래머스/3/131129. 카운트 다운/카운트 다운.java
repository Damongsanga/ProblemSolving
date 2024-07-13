import java.util.Arrays;

class Solution {
    public int[] solution(int target) {
        int[] able = new int[61];
        int[][] scorableDp = new int[target+1][2];
        for (int i = 1; i <= 20; i++) {
            able[i] = 1; // 싱글
            able[i*2] = 2; // 더블
            able[i*3] = 3; // 트리플
        }
        able[50] = 1; // 불

        // i 가 타켓 위치
        for (int i = 1; i <= target; i++) {
            if (i <= 60 && able[i] != 0){
                scorableDp[i][0] = 1;
                scorableDp[i][1] = able[i] == 1? 1 : 0;
                continue;
            }

            // j 가 탐색 위치
            for (int j = i-1; j >= 1 && i-j <= 60; j--) {
                // 기존 DP에서 1번의 추가 점수로 도달할 수 있을 때
                if (able[i-j] != 0){
                    // 처음 가능한 경우이거나, 탐색 DP에서 1번 더 던진 횟수가 현재 총 던진 횟수보다 적거나, 총 횟수는 같지만 싱글/불 갯수가 더 많을 때
                    if (scorableDp[i][0] == 0 || scorableDp[i][0] > scorableDp[j][0] + 1
                            || (scorableDp[i][0] == scorableDp[j][0] + 1 && scorableDp[i][1] <= scorableDp[j][1])) {
                        scorableDp[i][0] = scorableDp[j][0] + 1;
                        scorableDp[i][1] = scorableDp[j][1] + (able[i-j] == 1? 1 : 0);
                    }
                }
            }
        }

        return scorableDp[target];
    }
}
