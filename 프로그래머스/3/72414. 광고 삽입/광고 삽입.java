import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

class Solution {

    static final int HOURTOSEC = 3600;
    static final int MINUTETOSEC = 60;

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSec(play_time);
        int advTime = timeToSec(adv_time);

        long[] prefixSum = new long[playTime+1];

        for (String log : logs) {
            String[] tmp = log.split("-");
            prefixSum[timeToSec(tmp[0])]++;
            prefixSum[timeToSec(tmp[1])]--;
        }

        for (int i = 1; i <= playTime; i++) {
            prefixSum[i] += prefixSum[i-1];
        }

        for (int i = 1; i <= playTime; i++) {
            prefixSum[i] += prefixSum[i-1];
        }

        int max_idx = 0;
        long max_sum = prefixSum[advTime-1];

        for (int i = advTime; i < playTime; i++) {
            long sum = prefixSum[i] - prefixSum[i-advTime];
            if (sum > max_sum){
                max_sum = sum;
                max_idx = i-advTime+1;
            }
        }

        return secToTime(max_idx);
    }

    public static int timeToSec(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * HOURTOSEC + Integer.parseInt(st.nextToken()) * MINUTETOSEC + Integer.parseInt(st.nextToken());
    }

    public static String secToTime(int time){
        int hour = time / HOURTOSEC;
        int minute = time / MINUTETOSEC % MINUTETOSEC;
        int second = time % MINUTETOSEC % MINUTETOSEC;
        return ( hour > 9? hour : "0" + hour ) + ":" +  ( minute > 9? minute : "0" + minute ) + ":" + ( second > 9? second : "0" + second);
    }
}