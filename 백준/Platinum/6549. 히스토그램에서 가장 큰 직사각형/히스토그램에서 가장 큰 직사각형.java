import java.io.*;
import java.util.*;

public class Main {
    static class His {
        long h;
        int idx;

        public His(long h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if (N == 0)
                System.exit(0);

            Stack<His> stack = new Stack();
            stack.add(new His(0, -1));
            long max = 0;

            for (int idx = 0; idx < N; idx++) {
                long h = Long.parseLong(st.nextToken());

                while (!stack.isEmpty() && stack.peek().h > h) {
                    His his = stack.pop(); // 빠질 때 비교한다
                    max = Math.max(his.h * (idx - stack.peek().idx -1 ), max); // 앞쪽 비교
                }

                stack.push(new His(h, idx));

            }

            while (stack.peek().h != 0) {
                His his = stack.pop(); // 앞쪽 비교
                max = Math.max(his.h * (N - stack.peek().idx - 1), max);
            }

            System.out.println(max);
        }

    }

}