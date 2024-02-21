class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int srt = toSeconds(h1, m1, s1);
        int end = toSeconds(h2, m2, s2);
        answer = count(end) - count(srt);

        if (isOvelap(h1,m1,s1)) answer++;
        return answer;
    }

    private static int toSeconds(int h, int m, int s){
        return h * 3600 + m * 60 + s;
    }

    private static int count(int seconds){
        int res = 1; // 0시 0분 0초
        res += (int) (seconds * 59 / (10 * 360.0));
        res += (int) (seconds * 719 / (120 * 360.0));
        int twelve = toSeconds(12, 0, 0);
        if (seconds >= twelve)
            res--;
        return res;
    }

    private static boolean isOvelap(int h, int m, int s){
        double hourAngle = (h * 30 + m * 0.5 + s * 0.5 / 60) % 360;
        double minuteAngle = m * 6 + s * 0.1;
        double secondAngle = s * 6;
        return hourAngle == secondAngle || minuteAngle == secondAngle;
    }
}