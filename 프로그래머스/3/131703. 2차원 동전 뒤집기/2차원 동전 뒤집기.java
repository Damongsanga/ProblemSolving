import java.util.*;
import java.util.stream.IntStream;

class Solution {
    static int n;
    static int m;

    public int solution(int[][] beginning, int[][] target) {
        int answer = 987654321;
        n = beginning[0].length;
        m = beginning.length;
        boolean[][] change = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                change[i][j] = beginning[i][j] == target[i][j];
            }
        }

        boolean[] rows = new boolean[m];
        boolean[] rows2 = new boolean[m];
        boolean[] cols = new boolean[n];
        boolean[] cols2 = new boolean[n];

        if (change[0][0]) {
            answer = Math.min(function(change, rows, cols), answer);
            rows2[0] = true;
            cols2[0] = true;
            answer = Math.min(function(change, rows2, cols2), answer);
        } else {
            rows[0] = true;
            answer = Math.min(function(change, rows, cols), answer);
            cols2[0] = true;
            answer = Math.min(function(change, rows2, cols2), answer);
        }

        return answer;
    }

    static int function(boolean[][] change, boolean[] rows, boolean[] cols) {

        for (int i = 1; i < m; i++) {
            if (change[i][0])
                rows[i] = cols[0];
            else
                rows[i] = !cols[0];
        }

        for (int j = 1; j < n; j++) {
            if (change[0][j]) // 같으면, 변경사항이 없으면
                cols[j] = rows[0];
            else
                cols[j] = !rows[0];
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (change[i][j] != (rows[i] == cols[j]))
                    return -1;
            }
        }

        int res = 0;
        
        
        for (boolean b : rows){
            if (b) res++;
        }
        for (boolean b : cols){
            if (b) res++;
        }
        
        return res;

    }
}