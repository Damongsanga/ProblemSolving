import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public String[] solution(String[] s) {
        int N = s.length;

        for (int i = 0; i < N; i++) {
            char[] now = s[i].toCharArray();
            Deque<Character> deque = new ArrayDeque<>();
            int count = 0;

            for (int j = 0; j < now.length; j++) {
                char target = now[j];
                if (deque.size() >= 2 && target == '0'){
                    if (deque.peekLast() == '1'){
                        char polled = deque.pollLast();
                        if (deque.peekLast() == '1'){
                            deque.pollLast();
                            count++;
                            continue;
                        } else {
                            deque.addLast(polled);
                        }
                    }
                }
                deque.addLast(now[j]);
            }

            StringBuilder sb = new StringBuilder();
            while (!deque.isEmpty()){
                char target = deque.pollFirst();
                if (count > 0 && target == '1' && !(!deque.isEmpty() && deque.peekFirst() == '0')){
                    for (int j = 0; j < count; j++) {
                        sb.append("110");
                    }
                    count = 0;
                }
                sb.append(target);
            }

            if (count>0){
                for (int j = 0; j < count; j++) {
                    sb.append("110");
                }
            }

            s[i] = sb.toString();
        }

        return s;
    }


}
