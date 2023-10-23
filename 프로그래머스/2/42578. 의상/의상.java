import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap();
        for (String[] st : clothes){
            map.putIfAbsent(st[1], new ArrayList());
            map.get(st[1]).add(st[0]);
        }
        int answer = 1;
        for (String key : map.keySet()){
            answer *= (map.get(key).size()+1);
        }
        
        return answer-1;
    }
}