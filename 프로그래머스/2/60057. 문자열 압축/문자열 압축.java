class Solution {
    public int solution(String s) {
        int answer = 0;
        int l = s.length();
        int min = l;
        for (int i = 1; i <= l/2; i++) {
            int tmp = i;
            int flag = 1; // 반복횟수
            for (int j = i; j < l; j+=i) {
                // 마지막 비교문이 크기가 안맞으면 어자피 줄일 수 없음, 그냥 더하면 됨
                if (j+i > l){
                    tmp += l-j;
                    continue;
                }
                if (s.substring(j, j+i).equals(s.substring(j-i, j))){
                    flag++; // 반복횟수 증가
                    // 반복 횟수 자릿수에 따라 자릿수 조정
                    if (flag == 2 || flag == 10 || flag == 100 || flag == 1000) tmp++;
                } else {
                    flag = 1; // 반복횟수 리셋
                    tmp += i;
                }
            }
            min = Math.min(min, tmp);
        }
        return min;
    }
}