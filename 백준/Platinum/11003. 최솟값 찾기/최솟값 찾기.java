import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - L + 1) deque.pollFirst();
            while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]) deque.pollLast();
            deque.offerLast(i);
            answer.append(arr[deque.peekFirst()]).append(" ");
        }

        System.out.println(answer.toString().trim());

    }

}