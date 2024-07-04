import java.util.*;

/** 리틀 프렌즈 사천성
 * 알고리즘 : 구현
 * 핵심 아이디어 :
 *  1. DFS로 계속 탐색하면 안된다. (딱봐도 시간초과임)
 *  2. 문제에서 한번만 꺾을 수 있다고 했음으로 두 알파벳의 위치를 대각선 꼭지점으로 가지는 직사각형에서 시계방향, 반시계방향 경로 총 2개만 생각해야 한다.
 *  구현 방법 :
 *  1. 알파벳의 범위는 26으로 정해져있음으로 모든 알파벳의 위치를 기록해둘 charPositions 배열을 만든다
 *  2. 출력을 알파벳 순서대로 하라고 했음으로 A 부터 지울 수 있는지 탐색하여 지웠다면 다시 A부터 처음부터 탐색해야 한다.
 *  3. 탐색 과정은 쌍 증에서 첫 번째 알파벳을 a1, 두 번째 알파벳을 a2라고 하면 a1 r에서 a2 r까지 row 이동, a1 c에서 a2 c까지 col 이동하는 경로 1개랑
 *     a1 c에서 a2 c까지 col 이동하고, a1 r에서 a2 r까지 row 이동하는 경로, 총 2개만 보면 된다.
 *     탐색하는 방향은 중요하지 않으니 그냥 모두 증가하는 방향으로 구현했다.
 * */
class Solution {

    static int answer = 0;
    static int N;
    static List<Integer>[] adjacencyList;
    static boolean[] visited;
    static boolean[] lighted;

    public int solution(int n, int[][] lighthouse) {
        N = n;
        adjacencyList = new List[N+1];
        for (int i = 1; i < N+1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n-1; i++) {
            adjacencyList[lighthouse[i][0]].add(lighthouse[i][1]);
            adjacencyList[lighthouse[i][1]].add(lighthouse[i][0]);
        }

        visited = new boolean[N+1];
        isLightedDfs(1, 1);

        return answer;
    }

    private boolean isLightedDfs(int now, int root){
        // 제일 먼저 방문처리
        visited[now] = true;

        // 리프노드라면 등대 false
        if (now != root && adjacencyList[now].size() == 1) return false;

        // 리프 노트가 아니라면
        boolean isChildAllLighted = true;
        for (int next : adjacencyList[now]) {
            if (visited[next]) continue;
            isChildAllLighted &= isLightedDfs(next, root);
        }

        if (!isChildAllLighted){
            answer++;
            return true;
        } else {
            return false;
        }
    }
}