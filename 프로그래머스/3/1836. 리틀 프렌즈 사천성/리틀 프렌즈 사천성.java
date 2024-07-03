import java.util.*;

/** 자동완성
 * 알고리즘 : 정렬 & 투포인터
 * 핵심 아이디어 :
 *  1. 자동완성은 앞에서부터 탐색함으로 정렬 필요, 단어 수도 10만개임으로 가능
 *  2. 단어 길이 합이 100만 이하임을 주목. 만약 투포인터로 직전 단어와 한 알파벳씩 비교해도 200만 이하일 것임
 *  구현 방법 :
 *  1. 직전 단어와 투포인터로 맨 앞 index에서부터 알파벳이 같은지 비교
 *  2. 다른 순간까지 now 증가 (현재 단어를 구별하기 위해 입력해야 하는 단어 수)
 *  3. prev index는 현재 단어에 의해 갱신될 수 있음. 만약 prev가 현재 now 보다 더 작다면, 이는 갱신이 필요하다는 뜻
 *  4. 여기서 갱신을 잘 해주어야 하는데 글자수가 부족해서 now 만큼 입력을 못할 수 있음. 따라서 prev = Math.min(now, prev 단어 길이)
 *  5. 이제 prev 단어는 더이상 갱신될 일이 없음으로 답에 sum
 *  6. 마지막까지 for문을 돌고 마지막 글자의 now를 답에 sum해주면 끝
 * */
class Solution {

    static char[][] arr;
    static int[][] characters;

    public String solution(int m, int n, String[] board) {
        final int[] EMPTY_CHAR = {-1,-1,-1,-1};
        StringBuilder sb = new StringBuilder();

        characters = new int[26][4];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(characters[i], -1);
        }
        arr = new char[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
                if ('A' <= arr[i][j] && arr[i][j] <= 'Z'){
                    int idx = arr[i][j]-'A';
                    if (characters[idx][0] != -1){
                        count++;
                        characters[idx][2] = i;
                        characters[idx][3] = j;
                    } else {
                        characters[idx][0] = i;
                        characters[idx][1] = j;
                    }
                }
            }
        }

        for (int tc = 0; tc < count; tc++) {
            for (int i = 0; i < 26; i++) {
                if (characters[i][0] == -1) continue;

                if(!isMovable(i)) continue;

                arr[characters[i][0]][characters[i][1]] = '.';
                arr[characters[i][2]][characters[i][3]] = '.';
                characters[i] = EMPTY_CHAR;
                sb.append((char) ('A'+i));

                break;
            }
        }
        if (sb.length() != count) return "IMPOSSIBLE";
        return sb.toString();
    }

    private boolean isMovable(int i){
        int rMin = Math.min(characters[i][0], characters[i][2]);
        int rMax = Math.max(characters[i][0], characters[i][2]);
        int cMin = Math.min(characters[i][1], characters[i][3]);
        int cMax = Math.max(characters[i][1], characters[i][3]);
        return (isMovableRow(characters[i][1], rMin, rMax, i) && isMovableCol(characters[i][2], cMin, cMax, i))
                || (isMovableRow(characters[i][3], rMin, rMax, i) &&  isMovableCol(characters[i][0], cMin, cMax, i));
    }

    private boolean isMovableRow(int c, int rMin, int rMax, int idx) {
        for (int i = rMin; i <= rMax; i++) {
            if (arr[i][c] != '.' && arr[i][c] != (char) ('A'+idx)) return false;
        }
        return true;
    }

    private boolean isMovableCol(int r, int cMin, int cMax, int idx) {
        for (int i = cMin; i <= cMax; i++) {
            if (arr[r][i] != '.' && arr[r][i] != (char) ('A'+idx)) return false;
        }
        return true;
    }
}