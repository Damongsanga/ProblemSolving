import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        int len = s.length();
        for (int i = 1; i < len-1; i++){
            int v = Math.min(i, len - i - 1);
            int count = 1;
            for (int j = 1; j <= v; j++){
                if (s.charAt(i+j) != s.charAt(i-j)) break;
                count+=2;
            }
            answer = Math.max(answer, count);
        }
        
        if (len >= 2 && (s.charAt(0) == s.charAt(1) || s.charAt(len-1) == s.charAt(len-2)))
            answer = Math.max(answer, 2);
        
        for (int i = 1; i < len-2; i++){
            if (s.charAt(i) != s.charAt(i+1)) continue;
            int v = Math.min(i, len - i - 2);
            int count = 2;
            for (int j = 1; j <= v; j++){
                if (s.charAt(i+1+j) != s.charAt(i-j)) break;
                count+=2;
            }
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
}