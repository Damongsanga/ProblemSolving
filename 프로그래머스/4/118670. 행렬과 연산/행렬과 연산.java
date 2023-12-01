import java.util.LinkedList;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        LinkedList<LinkedList<Integer>> inL = new LinkedList<>();

        int r = rc.length;
        int c = rc[0].length;

        for (int i = 0; i < r; i++) {
            inL.add(new LinkedList<>());
        }

        for (int i = 0; i < r; i++) {
            l1.add(rc[i][0]);
            for (int j = 1; j < c-1; j++) {
                inL.get(i).add(rc[i][j]);
            }
            l2.add(rc[i][c-1]);
        }
        
        

        for (String op : operations){
            operate(l1,l2,inL,op);
        }
        
        
        int[][] answer = new int[r][c];
        for (int i = 0; i < r; i++) {
            answer[i][0] = l1.pollFirst();
            for (int j = 1; j < c-1; j++) {
                answer[i][j] = inL.get(i).pollFirst();
            }
            answer[i][c-1] = l2.pollFirst();
        }

        return answer;
    }

    static void operate(LinkedList<Integer> l1, LinkedList<Integer> l2, LinkedList<LinkedList<Integer>> inL, String op){
        if (op.equals("Rotate")){
            inL.getFirst().addFirst(l1.pollFirst());
            l2.addFirst(inL.getFirst().pollLast());
            inL.getLast().addLast(l2.pollLast());
            l1.addLast(inL.getLast().pollFirst());
        }
        if (op.equals("ShiftRow")){
            l1.addFirst(l1.pollLast());
            l2.addFirst(l2.pollLast());
            inL.addFirst(inL.pollLast());
        }
    }
}