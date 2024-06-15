import java.util.*;

/** 리코쳇 로봇
 * 알고리즘 : DFS
 * 핵심 아이디어 :
 * 1. 해당 위치까지 오는데 방문 배열이 boolean이 아닌 int로 해야함
 * 2. 원복하지 말고 해당 위치까지 오는 경우의 수가 더 작은 경우만 추가로 탐색
 * 3. 방금 왔던 방향으로는 탐색을 아예 안해버리면 시간이 절반 정도로 줌
 * */

class Solution {
    static long[] rr = {0,0,-1,1};
    static long[] rc = {-1,1,0,0};

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rSrt = x;
        long rEnd = x;
        long cSrt = y;
        long cEnd = y;

        for(int i = queries.length - 1; i >= 0 ; i--){
            int direction = queries[i][0];
            int dx = queries[i][1];
            if (rSrt != 0)
                rSrt = rSrt - rr[direction] * dx < 0 ? 0 : rSrt - rr[direction] * dx;
            if (rEnd != n-1)
                rEnd = rEnd - rr[direction] * dx >= n ? n-1 : rEnd - rr[direction] * dx;
            if (cSrt != 0)
                cSrt = cSrt - rc[direction] * dx < 0 ? 0 : cSrt - rc[direction] * dx;
            if (cEnd != m-1)
                cEnd = cEnd - rc[direction] * dx >= m ? m-1 : cEnd - rc[direction] * dx;

            if (rSrt > rEnd || cSrt > cEnd) return 0;

        }

        return (rEnd - rSrt + 1) * (cEnd - cSrt + 1);
    }


}