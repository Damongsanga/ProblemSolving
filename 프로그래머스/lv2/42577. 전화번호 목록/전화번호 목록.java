import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        outer : for(int i = 0; i < phone_book.length-1; i++){
            String now = phone_book[i];
            String next = phone_book[i+1];
            if (now.length() > next.length()) continue;
            
            for (int j = 0; j < now.length(); j++){
                if (now.charAt(j) != next.charAt(j)) continue outer;
            }
            return false;
        }
        
        return true;
    }
}