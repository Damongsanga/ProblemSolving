import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int N = number.length();
        ArrayDeque<Character> deque = new ArrayDeque();
        
        for(int i = 0; i < N; i++){
            while(k > 0 && !deque.isEmpty() && deque.peekLast() < number.charAt(i)){
                deque.pollLast();
                k--;
            }    
            deque.add(number.charAt(i));
        }
        while(k>0){
            deque.pollLast();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.pollFirst());
        }
        
        return sb.toString();
    }
}