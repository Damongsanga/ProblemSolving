import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] scores) {
        int answerA = scores[0][0];
        int answerB = scores[0][1];
        int answerSum = answerA + answerB;
        int n = scores.length;
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        int maxScore = scores[0][1];
        for (int i = 1; i < n; i++) {
            if (scores[i][1] < maxScore) {
                if (scores[i][0] == answerA && scores[i][1] == answerB) return -1;
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                maxScore = scores[i][1];
            }
        }

        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] + o2[1] - o1[0] - o1[1];
            }
        });

        int answer = 1;
        for (int i = 0; i < n; i++) {
            if (scores[i][0] + scores[i][1] > answerSum) answer++;
            else break;
        }

        return answer;
    }
}