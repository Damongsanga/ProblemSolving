import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int N = numbers.length;
        String[] numStr = new String[N];
        for (int i=0; i < N; i++){
            numStr[i] = numbers[i]+"";
        }
        Arrays.sort(numStr, (s1, s2) ->
            (s2+s1).compareTo(s1+s2)
        );

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(numStr[i]);
        }
        String answer = sb.toString();
        return answer.startsWith("0") ? "0" : answer;
    }
    
}