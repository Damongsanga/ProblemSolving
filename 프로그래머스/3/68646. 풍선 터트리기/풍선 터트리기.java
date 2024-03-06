import java.util.*;

class Solution {
    public int solution(int[] a) {

        int len = a.length;
        if (len <= 2){
            return len;
        }
        
        int[] minL = new int[len];
        minL[0] = a[0];
        for (int i = 1; i < len; i++){
            minL[i] = Math.min(minL[i-1], a[i]);
        }
        
        int[] minR = new int[len];
        minR[len-1] = a[len-1];
        for (int i = len-2; i >= 0; i--){
            minR[i] = Math.min(minR[i+1], a[i]);
        }
        
        int answer = 2;
        for(int i = 1; i < len-1; i++){
            int target = a[i];
            if (target >= minL[i-1] && target >= minR[i+1]) continue;
            answer++;
        }
        
        return answer;
    }
}