import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    static int weight = 0; // 모든 dice 값이 같은 경우는 예시에서 안주어짐
    static boolean[] answerList;
    static int n;

    public int[] solution(int[][] dice) {
        n = dice.length/2; // n은 1명이 가져가는 주사위 수
        boolean[] visited = new boolean[n*2];

        // 절반만 탐색하도록 맨 첫번째 주사위는 선택하여 backtracking
        visited[0] = true;
        backtracking(1, 0, visited, dice);

        int[] answer = new int[n];
        int idx = 0;
        for (int i = 0; i < n*2; i++) {
            if (answerList[i]) answer[idx++] = i+1;
        }

        return answer;
    }
    static void backtracking(int count, int last, boolean[] visited, int[][] dice){
        if (count == n){
            int win = method(visited, dice);
            if (weight < Math.abs(win)){ // 내가 고른 주사위의 반대 경우도 포함하도록
                weight = Math.abs(win);
                answerList = Arrays.copyOf(visited, n*2);
                if (win < 0) {
                    for (int i = 0; i < n * 2; i++) {
                        answerList[i] = !answerList[i];
                    }
                }
            }
            return;
        }

        for (int i = last+1; i < n*2; i++) {
            visited[i] = true;
            backtracking(count+1, i, visited, dice);
            visited[i] = false;
        }
    }

    static int method(boolean[] visited, int[][] dice){
        ArrayList<int[]> chosenDiceData = new ArrayList<>(); // 고른 주사위 6면들
        ArrayList<int[]> notChosenDiceData = new ArrayList<>(); // 안고른 주사위 6면들

        for (int i = 0; i < n*2; i++) {
            if (visited[i]) chosenDiceData.add(dice[i]);
            else notChosenDiceData.add(dice[i]);
        }

        int[] countSumOfChosenDices = new int[n*100+1]; // 주사위 한 명 최대값이 100임
        getDiceSumDFS(0, 0, countSumOfChosenDices, chosenDiceData);
        int[] countSumOfNotChosenDices = new int[n*100+1];
        getDiceSumDFS(0, 0, countSumOfNotChosenDices, notChosenDiceData);

        int winTotal = 0;
        int loseTotal = 0;
        int win = 0;
        int lose = 0; // 고른 지는 경우
        for (int i = n; i <= n * 100; i++) { // 슬라이딩 윈도우?
            win += countSumOfChosenDices[i-1];
            lose += countSumOfNotChosenDices[i-1];
            winTotal += countSumOfChosenDices[i] * (lose);
            loseTotal += countSumOfNotChosenDices[i] * (win);
        }

        return winTotal > loseTotal? winTotal : loseTotal * -1;
    }

    // 실제 카운트 값 계산
    static void getDiceSumDFS(int depth, int sum, int[] countSumOfDices, ArrayList<int[]> chosenDiceData){
        if (depth == n){
            countSumOfDices[sum]++;
            return;
        }

        for (int i = 0; i < 6; i++) {
            sum += chosenDiceData.get(depth)[i];
            getDiceSumDFS(depth+1, sum, countSumOfDices, chosenDiceData);
            sum -= chosenDiceData.get(depth)[i];
        }
    }


//    public static void main(String[] args) {
////        System.out.println(Arrays.toString(new Solution().solution(new int[][]{{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}})));
//        System.out.println(Arrays.toString(new Solution().solution(new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}})));
//    }
}