import java.util.*;

class Solution {
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};
    static class Node implements Comparable<Node>{
        int r; int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (o.r == this.r) return this.c-o.c;
            return this.r-o.r;
        }

        public boolean equals(Object o){
            Node n = (Node) o;
            return (n.r == this.r) && (n.c == this.c);
        }

        @Override
        public String toString() {
            return r + " " + c;
        }
    }
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        ArrayList<ArrayList<Node>[]> block = new ArrayList();
        ArrayList<ArrayList<Node>> board = new ArrayList();
        int N  = table.length;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (table[i][j] == 1){
                    ArrayList<ArrayList<Node>> floodfillList = floodfill(i, j, 1, table, N);
                    int ffsize = floodfillList.size();
                    ArrayList<Node>[] tmp = new ArrayList[ffsize*4];
                    for (int k = 0; k < ffsize * 4; k++){
                        if (k < ffsize) tmp[k] = floodfillList.get(k);
                        else {
                            tmp[k] = rotate(tmp[k % ffsize + ((k/ffsize)-1) * ffsize]);
                        }
                    }
                    block.add(tmp);
                }
                if (game_board[i][j] == 0){
                    board.add(floodfill(i, j, 0, game_board, N).get(0));
                }
            }
        }
        

        boolean[] visited = new boolean[block.size()];
        for (int i = 0; i < board.size(); i++){
            for (int j = 0; j < block.size(); j++){
                if (visited[j]) continue;
                int sizeBd = board.get(i).size();
                int sizeBk = block.get(j)[0].size();
                if (sizeBk != sizeBd) continue;

                boolean flag = false;
                for (int k =0; k < block.get(j).length; k++){
                    flag = true;
                    for (int m= 0; m < sizeBd; m++){
                        if (!board.get(i).get(m).equals(block.get(j)[k].get(m))){
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        answer += sizeBk;
                        System.out.println(i + " " + j + " " + sizeBk);
                        visited[j] = true;
                        break;
                    }
                }
                if (flag) break;
            }
        }

        return answer;
    }

    static ArrayList<Node> rotate(ArrayList<Node> list){
        ArrayList<Node> newList = new ArrayList();
        for (Node n : list){
            newList.add(new Node(n.c, n.r * -1));
        }
        Collections.sort(newList);
        return newList;
    }

    static ArrayList<ArrayList<Node>>floodfill(int r, int c, int check, int[][] t, int N){
        ArrayList<ArrayList<Node>> bigList = new ArrayList();
        ArrayList<Node> list = new ArrayList();
        list.add(new Node(0,0));

        Queue<Node> queue = new ArrayDeque();
        queue.add(new Node(r,c));
        t[r][c] = -1;

        while (!queue.isEmpty()){
            Node now = queue.poll();

            for(int i = 0; i < 4; i++){
                int nr = now.r + rr[i];
                int nc = now.c + rc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (t[nr][nc] != check) continue;
                t[nr][nc] = -1;
                list.add(new Node(nr-r, nc-c));
                queue.add(new Node(nr, nc));
            }
        }
        Collections.sort(list);
        bigList.add(list);

        for(int i = 1; i < list.size(); i++){
            ArrayList<Node> tmp = new ArrayList();
            Node offset = list.get(i);
            for (int j =0; j< list.size(); j++){
                Node n = list.get(j);
                tmp.add(new Node (n.r - offset.r, n.c - offset.c));
            }
            bigList.add(tmp);
        }

        return bigList;

    }
}