import java.util.*;

/** 캠핑
 * 알고리즘 : 구현
 * 핵심 아이디어 :
 *  1. N이 5000임으로 N^2 * logN까지 사용 가능. 이번 풀이는 O(N^2)임
 *  2. 높이 기준으로 오름차순 정렬, 같은 높이에서는 왼쪽으로 정렬
 *  3. 낮은 높이에서부터 탐색하면서 아래 높이는 고려하지 않아 중복으로 선택되는 경우 방지
 *  4. 현재 쐐기 기준으로 순서대로 캠프를 칠 수 있는지 하나하나 확인하는데 만약 전에 쳤던 텐트에 들어오면 배제 -> 허용 범위로 표현
 *  5.
 *  헤멘 부분 :
 *  1. 같은 높이일 경우 허용 범위를 무작정 좁히면 안됨. 같은 높이임으로 경계선에 걸쳐서 모두 선택될 수 있어야 함.
 *  2. 따라서 전체 허용 범위을 기준으로 쐐기를 박을 수 있을지 판단해야한다. 이는 매번 갱신되는 것이 아니라 높이가 바뀔 때만 갱신해주어야 한다.
 *  3. 현재 높이에서의 허용 범위는 임시 변수로 저장하고 "높이가 변경될 때만" 전체 허용 범위를 갱신해주어야 한다.
 *  4. 따라서 반복문에서 가장 먼저 해야 할 것은 높이가 바뀌는지 여부를 체크하는 것이다.
 * */
class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        // 높이 기준으로 오름차순 정렬. 같은 높이에서는 왼쪽에서 오른쪽으로 정렬
        Arrays.sort(data, (a,b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < n; i++) {
            int[] target = data[i];
            // 전체 허용 범위
            int maxX = Integer.MAX_VALUE;
            int minX = Integer.MIN_VALUE;

            // 현재 높이에서의 허용 범위
            int tmpMaxX = maxX;
            int tmpMinX = minX;
            int nowY = target[1];

            for (int j = i+1; j < n; j++) {
                int[] now = data[j];

                // 전체 허용 범위 갱신 : 높이가 바뀔 때 현재 높이에서의 허용 범위가 전체 허용 범위가 됨
                if (now[1] != nowY){
                    nowY = now[1];
                    maxX = tmpMaxX;
                    minX = tmpMinX;
                }

                // 판단 : 넓이가 0이거나 현재 허용 범위를 넘어가는 경우는 스킵
                if (target[0] == now[0] || target[1] == now[1]) continue;
                if (now[0] > maxX || now[0] < minX) continue;

                // 현재 높이에서의 허용 범위 갱신
                if (now[0] < target[0]) tmpMinX = Math.max(tmpMinX, now[0]);
                else tmpMaxX = Math.min(tmpMaxX, now[0]);
                answer++;
            }
        }

        return answer;
    }
}