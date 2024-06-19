import java.util.*;

class Solution {
    public int solution(String[] words) {
        Arrays.sort(words);
        int answer = 0;
        int prev = 1;
        int now = 0;

        for (int i = 1; i < words.length; i++) {
            int idxPrev = 0;
            int idxNow = 0;
            now = 1;
            while (idxPrev < words[i-1].length() && idxNow < words[i].length()){
                if (words[i-1].charAt(idxPrev++) != words[i].charAt(idxNow++)) break;
                now++;
            }
            if (prev < now) prev = Math.min(now, words[i-1].length());
            answer += prev;
            prev = now;
        }

        answer += now;

        return answer;
    }
}