import java.util.Arrays;

class Solution {
    public int solution(String arr[]) {
        int[] num = new int[arr.length/2 + 1];
        String[] opp = new String[arr.length/2];
        int N = num.length;
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(arr[i*2]);
        }
        for (int i = 0; i < N-1; i++) {
            opp[i] = arr[i*2+1];
        }

        int[][] dpmax = new int[N][N];
        int[][] dpmin = new int[N][N];


        for (int i = 0; i < N; i++) {
            dpmax[0][i] = num[i];
            dpmin[0][i] = num[i];
        }

        for (int i = 1; i < N; i++) {
            Arrays.fill(dpmax[i], Integer.MIN_VALUE);
            Arrays.fill(dpmin[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N-i; j++) {
                for (int k = 0; k < i; k++) {
                    if (opp[j+k].equals("+")) {
                        if (dpmax[i][j] < dpmax[k][j] + dpmax[i-k-1][j+k+1]) dpmax[i][j] = dpmax[k][j] + dpmax[i-k-1][j+k+1];
                        if (dpmin[i][j] > dpmin[k][j] + dpmin[i-k-1][j+k+1]) dpmin[i][j] = dpmin[k][j] + dpmin[i-k-1][j+k+1];
                    }
                    if (opp[j+k].equals("-")) {
                        if (dpmax[i][j] < dpmax[k][j] - dpmin[i-k-1][j+k+1]) dpmax[i][j] = dpmax[k][j] - dpmin[i-k-1][j+k+1];
                        if (dpmin[i][j] > dpmin[k][j] - dpmax[i-k-1][j+k+1]) dpmin[i][j] = dpmin[k][j] - dpmax[i-k-1][j+k+1];
                    }
                }
            }
        }

        return dpmax[N-1][0];
    }
}