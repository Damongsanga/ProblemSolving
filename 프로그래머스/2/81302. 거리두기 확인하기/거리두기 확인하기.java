import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Node {
        int r; int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};

    public int[] solution(String[][] places) {
        int n = places.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = isValid(places[i]);
        }
        return answer;
    }

    static int isValid(String[] place) {
        int n = place.length;
        char[][] map = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        List<Node> pList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String tmp = place[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = tmp.charAt(j);
                if (tmp.charAt(j) == 'P'){
                    pList.add(new Node(i,j));
                }
            }
        }

        for(Node node: pList){
            for (int i = 0; i < 4; i++) {
                int nr = node.r + rr[i];
                int nc = node.c + rc[i];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (map[nr][nc] == 'X') continue;
                if (map[nr][nc] == 'P') return 0;
                if (visited[nr][nc]) return 0;
                visited[nr][nc] = true;
            }
        }
        return 1;
    }
}