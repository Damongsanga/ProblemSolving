import java.io.*;
import java.util.*;

public class Main {
    static int[] rr = {-1,0,1,0};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int len = N-K; // 제출값의 길이
        String input = br.readLine();

        // 맨 앞자리가 클 수록 큰 수가 나오기 때문에 그리디하게 접근할 수 있음
        // 맨 앞 자리수가 제일 클 수록 좋음으로 매 숫자마다 덱을 peek하여 더 크거나 같은 수가 나올 때까지 poll 한다. 
        // poll 할 때마가 K를 1씩 감소시키고 K가 0이면 더이상 뽑을 수 없음으로 그 뒤로는 판단할 필요 없이 그대로 모두 덱에 추가한다.

        // 덱으로 한 이유, 로직은 스택만 필요하나 뽑을 때 순서가 뒤집힘으로 다시 뒤집는 연산을 하지 않기 위해
        Deque<Integer> deque = new ArrayDeque<>();
        int idx = 0;
        while(idx < N) {
        	int tmp = input.charAt(idx) - '0';
        	while (!deque.isEmpty() && K > 0 && deque.peekLast() < tmp) {
        		deque.pollLast();
        		K--;
        	}
        	deque.addLast(tmp);
        	idx++;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
			sb.append(deque.pollFirst());
		}
        System.out.println(sb);



    }
}