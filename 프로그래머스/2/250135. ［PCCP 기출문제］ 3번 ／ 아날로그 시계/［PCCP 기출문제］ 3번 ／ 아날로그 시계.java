import java.util.*;

/** 아날로그 시계
 *
* */
class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int minuteCount = minuteCount(timeToSec(h1, m1, s1), timeToSec(h2, m2, s2), initMinuteAngle(m1, s1));
        int hourCount = hourCount(timeToSec(h1, m1, s1), timeToSec(h2, m2, s2), initHourAngle(h1, s1));
        System.out.println("minuteCount = " + minuteCount);
        System.out.println("hourCount = " + hourCount);

        int count = minuteCount + hourCount;
        if (h1 == 0 && m1 == 0 && s1 == 0) count--;
        if (timeToSec(h1, m1, s1) <= 12 * 3600 && 12 * 3600 <= timeToSec(h2, m2, s2)) count--;

        return count;
    }

    private int timeToSec(int h, int m, int s){
        return 3600 * h + 60 * m + s;
    }

    private int minuteCount(int srt, int end, double initAngle){
//        System.out.println("min srt = " + srt);
//        System.out.println("min end = " + end);
//        System.out.println("min initAngle = " + initAngle);
        return (int) ((6 - 0.1) * (end-srt) + initAngle)/ 360;
    }

    private int hourCount(int srt, int end, double initAngle){
//        System.out.println("hour srt = " + srt);
//        System.out.println("hour end = " + end);
//        System.out.println("hour initAngle = " + initAngle);
        return (int) ((6 - (double) 1 / 120) * (end-srt) + initAngle)/ 360;
    }

    private double initMinuteAngle(int m, int s){
        if (6 * m + 0.1 * s >= 6 * s){
           return 360 - 6 * (m - s) + 0.1 * s;
        }
        return 6 * (s - m);
    }

    private double initHourAngle(int h, int s){
        if (h >= 12) h -= 12;
        if (h * 30 + (double) 1 / 120 * s >= s * 5){
            return 360 - (30 * h - 6 * s) + (double) 1 / 120 * s;
        }
        return 6 * s - 30 * h;
    }

}