import java.util.*;

/** 등대
 * 알고리즘 : 트리 탐색
 * 핵심 아이디어 :
 *  1. 노드가 N개, 연결 관계가 N-1개임으로 트리임을 알 수 있다.
 *  2. 어떤 경로에서도 연속으로 등대가 없을 수 없다 => 트리의 자식 중 하나라도 등대가 안켜져있다면 자신이 켜져있어야 한다.
 *  3. 리프노드는 무조건 꺼져있어야 한다.
 *  헤멘 부분 :
 *  1. 처음에 단순 DFS로 접근. 지금 내 기준에서 켜져 있으면 다음엔 끄거나 키거나, 내가 꺼져있으면 다음은 무조건 켜는 방식으로 -> 딱봐도 시간 초과날 것 같았음
 *  2. 다음엔 최악의 경우를 생각해서, 가장 긴 경로를 기준으로 /2 한 값을 기준으로 생각하려고 했으나 중간에 다른 경로로 뻗어나가는 것을 고려하는 것이 복잡했음
 *  3. 트리임을 나중에 눈치채고 리프노드가 꺼져있음을 기준으로 트리 탐색을 생각 (후위 순회 개념으로 접근)
 *  4. 첫 제출이 틀렸는데, 리프노드 판별법이 단순하게 접근 경로가 1인 것으로 판단하여 루트노드도 리프노드로 인식될 수 있었음. 이 부분 개선하여 통과
 * */
class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Arrays.sort(data, (a,b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < n; i++) {
            int[] target = data[i];
            int maxX = Integer.MAX_VALUE;
            int minX = Integer.MIN_VALUE;
            int nowY = target[1];
            int tmpMaxX = maxX;
            int tmpMinX = minX;
            for (int j = i+1; j < n; j++) {
                int[] now = data[j];
                if (now[1] != nowY){
                    nowY = now[1];
                    maxX = tmpMaxX;
                    minX = tmpMinX;
                }
                
                if (target[0] == now[0] || target[1] == now[1]) continue;
                if (now[0] > maxX || now[0] < minX) continue;

                if (now[0] < target[0]) tmpMinX = Math.max(tmpMinX, now[0]);
                else tmpMaxX = Math.min(tmpMaxX, now[0]);
                answer++;
            }
        }

        return answer;
    }
}