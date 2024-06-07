import java.util.*;

/** 석유 시추
 * 풀었던 문제여서 가볍게 복습만 함
 * 알고리즘 : 플러드필
 *
 *
 *
 * */

class Solution {

    static int[] rr = {1,0,-1,0};
    static int[] rc = {0,1,0,-1};
    static int N;
    static int M;


    static class Node {
        int r; int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        
        int num = 1;
        // 석유 덩어리들에 대해 번호를 붙여 (num) 각 석유 덩어리들의 사이즈를 countList에 저장
        List<Integer> countList =  new ArrayList<>();
        countList.add(0); // 0은 쓰레기값

        // 어떤 위치에 몇번째 석유덩어리가 있는지 기록
        int[][] area = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] != 0 && area[i][j] == 0){
                    countList.add(floodFill(i, j, num++, area, land));
                }
            }
        }

        // 석유관마다 채굴할 수 있는 석유 양 계산. 같은 석유덩어리에 대해 중복계산하지 않도록 Set을 사용
        for (int j = 0; j < M; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                set.add(area[i][j]);
            }
            answer = Math.max(answer, set.stream().mapToInt(Integer::intValue).map(countList::get).sum());
        }

        return answer;
    }

    static int floodFill(int r, int c, int num, int[][] area, int[][] land){
        int result = 1;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(r, c));
        area[r][c] = num;

        while (!queue.isEmpty()){
            Node now  = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now.r + rr[i];
                int nc = now.c + rc[i];
                if (nr < 0 || nc < 0 || nr >=N || nc >= M) continue;
                if (land[nr][nc] == 0) continue;
                if (area[nr][nc] != 0) continue;
                area[nr][nc] = num;
                result++;
                queue.add(new Node(nr, nc));
            }
        }

        return result;

    }

}