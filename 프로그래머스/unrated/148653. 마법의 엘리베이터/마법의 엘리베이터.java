import java.util.ArrayList;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        char[] arr = (storey + "").toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i] - '0';
            if (i == 0) {
                if (tmp == 10) answer += 1;
                else if (tmp > 5) answer += 11 - tmp;
                else answer += tmp;
                continue;
            }
            if (tmp > 5 || (tmp == 5 && arr[i - 1] - '0' >= 5)) {
                answer += 10 - tmp;
                arr[i - 1]++;

            } else {
                answer += tmp;
            }
        }
        return answer;
    }


}