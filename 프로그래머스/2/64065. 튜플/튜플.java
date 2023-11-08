import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] strings = s.substring(2, s.length()-2).split("\\},\\{");
        int N = strings.length;
        Arrays.sort(strings, (o1,o2) -> o1.length() - o2.length());
        int[] answer = new int[N];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++){
            String[] tmp = strings[i].split(",");
            for (int j = 0; i <= i; j++){
                int setSize = set.size();
                int tmpInt = Integer.parseInt(tmp[j]);
                set.add(tmpInt);
                if (set.size() != setSize){
                    answer[i] = tmpInt;
                    break;
                }
                
            }
        }
        
        return answer;
    }
}