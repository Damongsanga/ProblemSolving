import java.util.*;

class Solution {

    static final int MAX_TIME = 10000;
    
    public int solution(int n, int[] cores) {
        

        int l = 0;
        int r = n * MAX_TIME;
        
        while(l < r){
            int mid = (l + r)/2;
            if (calculate(mid, cores) >= n) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        
        int time = r-1;
        int count = cores.length;
        
        for (int c : cores){
            count += time/c;
        }
        
        int idx = 0;
        time++;
        while(count < n && idx < cores.length){
            if (time % cores[idx] == 0) {
                count++;
            }
            idx++;
        }
        
        return idx;
    }
    
    private int calculate(int time, int[] cores){
        int answer = cores.length;
        
        if (time == 0) return answer;
        
        for(int c : cores){
            answer += time/c;
        }
        
        return answer;
    }
}