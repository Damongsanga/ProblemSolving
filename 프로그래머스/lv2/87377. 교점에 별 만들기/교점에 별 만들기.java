import java.util.ArrayList;

class Solution {
    public String[] solution(int[][] line) {
        long min_x = Long.MAX_VALUE;
        long min_y = Long.MAX_VALUE;
        long max_x = Long.MIN_VALUE;
        long max_y = Long.MIN_VALUE;
        ArrayList<long[]> list = new ArrayList<>();

        for (int i = 0; i < line.length; i++) {
            for (int j = i+1; j < line.length; j++) {
                long A = line[i][0]; long B = line[i][1]; long E = line[i][2];
                long C = line[j][0]; long D = line[j][1]; long F = line[j][2];
                if (A * D - B * C != 0 && (B * F - E * D) % (A * D - B * C) == 0 && (E * C - A * F) % (A * D - B * C) == 0) {
                    long b = (B * F - E * D) / (A * D - B * C);
                    min_x = Math.min(min_x, b);
                    max_x = Math.max(max_x, b);
                    long c = (E * C - A * F) / (A * D - B * C);
                    min_y = Math.min(min_y, c);
                    max_y = Math.max(max_y, c);
                    list.add(new long[] {b,c});
                }
            }
        }

        boolean[][] arr = new boolean[(int) (max_x - min_x + 1)][(int)(max_y - min_y + 1)];
        for (long[] star : list) {
            arr[(int)(star[0]-min_x)][(int)(star[1]-min_y)] = true;
            System.out.println(star[0] + " " + star[1]);
        }

        String[] answer = new String[(int)(max_y - min_y+1)];
        for (int i = 0; i <= max_y - min_y ; i++) {
            char[] tmp = new char[(int)(max_x - min_x+1)];
            for (int j = 0; j <= max_x - min_x; j++) {
                tmp[j] = arr[j][i] ? '*' : '.';
            }
            answer[answer.length -1 - i] = String.valueOf(tmp);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][] {{0,1,-1}, {1,0,-1}, {1,0,1}}));
    }
}