import java.util.Arrays;
import java.util.Comparator;

class Solution {
    static int answer = 987654321;
    public int solution(int[] picks, String[] minerals) {
        answer = 987654321;
        int[] minerals2 = new int[minerals.length];
        for (int i = 0; i < minerals.length; i++) {
            switch(minerals[i]){
                case "diamond":
                    minerals2[i] = 25;
                    break;
                case "iron":
                    minerals2[i] = 5;
                    break;
                case "stone":
                    minerals2[i] = 1;
            }
        }

        //곡괭이 수
        int picknum = 0;
        for (int i = 0; i < 3; i++) {
            picknum += picks[i];
        }

        backtracking(0, picknum, minerals2, picks, 0);
        return answer;
    }

    public static void backtracking(int depth, int picknum, int[] minerals2, int picks[], int pirodo){

        // 광물을 모두 캐거나 곡괭이가 다 떨어지면 리턴
        if (depth >= minerals2.length || picknum == 0) {
            answer = Math.min(answer, pirodo);
            return;
        }

        for (int i = 0; i < picks.length; i++) {
            int addvalue = 0;
            if (picks[i] > 0){
                switch(i){
                    case 0:
                        for (int j = 0; j < 5 && depth+j < minerals2.length; j++) {
                            addvalue += 1; // diamind 곡괭이는 무관하게 1
                        }
                        break;
                    case 1:
                        for (int j = 0; j < 5 && depth+j < minerals2.length; j++) {
                            addvalue += minerals2[depth+j] <= 5 ? 1: 5; // iron. stone은 피로도 1, diamond는 5 추가
                        }
                        break;
                    case 2:
                        for (int j = 0; j < 5 && depth+j < minerals2.length; j++) {
                            addvalue += minerals2[depth+j]; // stone 곡괭이는 표대로 추가;
                        }
                }
                // 곡괭이 감소
                picks[i]--;
                picknum--;
                backtracking(depth+5, picknum, minerals2, picks, pirodo + addvalue);
                // 원복 (백트랙킹)
                picknum++;
                picks[i]++;
            }
        }
    }

}