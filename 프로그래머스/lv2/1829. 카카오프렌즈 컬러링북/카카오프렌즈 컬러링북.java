import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

class Node{
    int r; int c;
    Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        Queue<Node> queue = new ArrayDeque<>();
        int[] rr = {-1,0,1,0};
        int[] rc = {0,1,0,-1};

        // 모든 영역에 대해서 bfs 탐색
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int tmp = picture[i][j];
                int count = 0;
                // bfs
                if (tmp != 0){
                    numberOfArea++; // 영역 개수 카운트
                    queue.add(new Node(i,j));
                    picture[i][j] = 0;
                    count++; // 영역 크기 카운트

                    while (!queue.isEmpty()){
                        Node nd = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = nd.r + rr[k];
                            int c = nd.c + rc[k];
                            if (r < 0 || c < 0 || r >= m || c >= n) continue;// 영역 벗어났을 때 제외
                            if (picture[r][c] == 0) continue; // 해당 영역이 비어있을 때 제외
                            if (picture[r][c] == tmp){ // 해당 영역이 처음 탐색한 영역과 같은 색일 때
                                queue.add(new Node(r,c));
                                picture[r][c] = 0; // 다시 넣지 않도록 영역을 비운다
                                count++; // 영역 크기 카운트
                            }

                        }
                    }
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

}