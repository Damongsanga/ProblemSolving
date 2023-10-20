class Solution {
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};
    static class Node {
        int r; int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        ArrayList<ArrayList<Node>[]> block = new ArrayList();
        ArrayList<ArrayList<Node>[]> board = new ArrayList();
        int N  = table.length();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (table[i][j] == 1){
                    ArrayList<Node>[] tmp = new ArrayList[4];
                    tmp[0] = floodfill(i, j, 1, table, N);
                    for (int k = 1; k < 4; k++){
                        tmp[i] = rotate(tmp[i-1]);
                    }
                    block.add(tmp);
                }
                if (game_board[i][j] == 1){
                    ArrayList<Node>[] tmp = new ArrayList[4];
                    tmp[0] = floodfill(i, j, 0, game_board, N);
                    for (int k = 1; k < 4; k++){
                        tmp[i] = rotate(tmp[i-1]);
                    }
                    block.add(tmp);
                }
            }
        }
        
        for (int i = 0; i < block.size(); i++){
            
        }
        
        return answer;
    }
    
    static ArrayList<Node> rotate(ArrayList<Node> list){
        ArrayList<Node> newList = new ArrayList();
        for (Node n : list){
            newList.add(new Node(n.c, n.r * -1));
        }
        return newList;
    }
    
    static ArrayList<Node> floodfill(int r, int c, int check, int[][] t, int N){
        ArrayList<Node> list = new ArrayList();
        list.add(new Node(0,0));
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(new node(r,c));
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
        
        return list;
        
    }
}