import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int N = str.length();

        Deque<Character> deque = new ArrayDeque<>();
        deque.add(str.charAt(0));
        
        // 매 글자가 현재 deque 의 맨 앞보다 작거나 같으면 앞으로 넣고 아니면 뒤로 넣는다
        // 순서를 뒤집는다는 것은 뒤집는 글자들의 중간 순서들은 바뀔 수 없고 새로운 글자가 처음이나 끝에만 들어갈 수 있다는 데서 아이디어를 얻음
        for (int i = 1; i < N; i++) {
            char head = deque.peekFirst();
            char c = str.charAt(i);
            if (c <= head) deque.addFirst(c);
            else deque.addLast(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(deque.pollFirst());
        }
        System.out.println(sb);

    }
}