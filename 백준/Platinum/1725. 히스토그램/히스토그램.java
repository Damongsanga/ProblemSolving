import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        int idx; int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(-1,0));
        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            int now = arr[idx];
            while (!stack.isEmpty() && now < stack.peek().val){
                Node node = stack.pop();
                answer = Math.max((idx - stack.peek().idx - 1) * node.val, answer);
            }
            stack.push(new Node(idx, now));
        }

        while(stack.peek().val != 0){
            Node node = stack.pop();
                answer = Math.max((N - stack.peek().idx - 1)* node.val, answer);
        }

        System.out.println(answer);
    }

}