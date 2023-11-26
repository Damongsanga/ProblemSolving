import java.util.*;

class Solution {

    public class Node {
        Node prev;
        Node next;
        int idx;

        public Node(int idx) {
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", idx=" + idx +
                    '}';
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Node head = new Node(-1);
        Node tail = head;
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            add(tail, new Node(i));
            tail = tail.next;
        }

        Node now = move(head, 'D', k+1);


        for (int i = 0; i < cmd.length; i++) {
            char c = cmd[i].charAt(0);

            switch(c){
                case 'U':
                case 'D':
                    now = move(now, c, Integer.parseInt(cmd[i].split(" ")[1]));
                    break;
                case 'C':
                    stack.push(now);
                    now = pop(now);
                    break;
                case 'Z':
                    unPop(stack.pop());
            }
        }

        StringBuilder sb = new StringBuilder();

        head = head.next;
        for (int i = 0; i < n; i++) {
            if (head != null && i == head.idx){
                head = head.next;
                sb.append("O");
            } else {
                sb.append("X");
            }
        }
        return sb.toString();
    }

    static Node add(Node origin, Node now){
        origin.next = now;
        now.prev = origin;
        return now;
    }

    static Node move(Node now, char c, int n){
        while(n != 0){
            if (c == 'U')
                now = now.prev;
            else if (c == 'D')
                now = now.next;
            n--;
        }
        return now;
    }

    static Node pop(Node now){
        now.prev.next = now.next;
        if (now.next != null)
            now.next.prev = now.prev;
        if (now.next == null) return now.prev;
        return now.next;
    }

    static void unPop(Node now){
        now.prev.next = now;
        if (now.next != null)
            now.next.prev = now;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(8,7, new String[] {"C", "C","Z"}));
    }

}