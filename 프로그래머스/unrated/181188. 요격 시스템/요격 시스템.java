import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);
        int answer = 0;
        int end = 0;
        for (int i = 0; i < targets.length; i++) {
            if (end <= targets[i][0]) {
                answer++;
                end = targets[i][1];
            } else {
                end = Math.min(end, targets[i][1]);
            }
        }
        return answer;
    }
}