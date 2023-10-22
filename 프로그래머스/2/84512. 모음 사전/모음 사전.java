import java.util.*;

class Solution {
    static int answer;
    
    public int solution(String word) {
        answer = 0;
        
        char[] arr = {'A', 'E', 'I', 'O', 'U'};
        
        DFS(0, new int[]{1,0,0,0,0}, 0, arr, word);
        
        return answer;
    }
    static int DFS(int depth, int[] value, int count, char[] arr, String word){
        if (depth == 5){
            count++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< 5; i++){
                if (value[i] != 0) sb.append(arr[value[i]-1]);
            }
            if (sb.toString().equals(word)){
                answer = count;
            }
            return count;
        }
        
        
        if (answer != 0){
            return answer;
        }
        
        for (int i =0; i<6; i++){
            if (depth==0 && i == 0){
                continue;
            } else if (depth > 0 && value[depth-1]==0 && i != 0){
                continue; 
            }
            else {
                value[depth] = i;
                count = DFS(depth+1, value, count, arr, word);
            }
        }
        
        return count;
    }
    
}