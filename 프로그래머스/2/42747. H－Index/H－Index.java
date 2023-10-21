import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int N = citations.length;
        int answer = 0;
        int maxh = citations[N-1];
        
        for (int i = 0; i < maxh; i++){
            int b = Arrays.binarySearch(citations,i);
            if (b < 0) b = (b+1) * -1;
            if (i <= N-b && i >= b){
                answer = Math.max(answer, i);
            }
        }

        return answer;
    }
}