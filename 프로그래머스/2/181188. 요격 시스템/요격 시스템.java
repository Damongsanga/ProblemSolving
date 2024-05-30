import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] - b[0]);
//        int l = targets[0][0];
        int r = targets[0][1];
        int answer = 1;

        for (int i = 1; i < targets.length; i++) {
            int[] target = targets[i];
            if (target[0] >= r){
                answer++;
//                l = target[0];
                r = target[1];
            } else {
//                l = Math.max(l, target[0]);
                r = Math.min(r, target[1]);
            }
        }

        return answer;

    }
}