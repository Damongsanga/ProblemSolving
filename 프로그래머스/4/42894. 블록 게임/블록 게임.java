import java.util.*;

class Solution {

    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};

    static class Block {
        int num;
        List<Node> required;

        public Block(int num, List<Node> required) {
            this.num = num;
            this.required = new ArrayList<>(required);
        }

        public String toString(){
            return num + "";
        }
    }

    static class Node {
        int r; int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] board) {

        int N = board.length;
        List<Block> blocks = new ArrayList<>();

        boolean[] unableNumber = new boolean[201];
        unableNumber[0] = true;

        for (int i = N-1; i > 0; i--) {
            for (int j = 0; j < N; j++) {
                int number = board[i][j];
                if (unableNumber[number]) continue;
                if (j == N-1 || number != board[i][j+1]) unableNumber[number] = true;
                else {
                    if (j == N-2 || number != board[i][j+2]){
                        if (number == board[i-1][j]) blocks.add(new Block(number, List.of(new Node(i-1, j+1))));
                        else blocks.add(new Block(number, List.of(new Node(i-1, j))));
                        j++;
                    } else {
                        if (number == board[i-1][j]) blocks.add(new Block(number, List.of(new Node(i-1, j+1), new Node(i-1, j+2))));
                        else if (number == board[i-1][j+1]) blocks.add(new Block(number, List.of(new Node(i-1, j), new Node(i-1, j+2))));
                        else blocks.add(new Block(number, List.of(new Node(i-1, j), new Node(i-1, j+1))));
                        j += 2;
                    }
                }
            }
        }

        int answer = 0;

        boolean[] broken = new boolean[201];
        broken[0] = true;

        while (true){
            int count = blocks.size();
            List<Node> nodesToRemove = new ArrayList<>();
            List<Block> blocksToRemove = new ArrayList<>();

            for (Block block: blocks) {
                for (Node node : block.required){
                    for (int r = 0; r < N; r++) {
                        if (broken[board[r][node.c]]) continue;
                        if (r-1 == node.r){
                            nodesToRemove.add(node);
                        } else {
                            break;
                        }
                    }
                }

                block.required.removeAll(nodesToRemove);
                nodesToRemove.clear();

                if (block.required.isEmpty()){
                    broken[block.num] = true;
                    blocksToRemove.add(block);
                    answer++;
                }
            }

            blocks.removeAll(blocksToRemove);
            blocksToRemove.clear();

            if (count == blocks.size()) break;
        }


        return answer;


    }
}