import java.io.*;
import java.util.*;

class Node{
    int r; int c;
    Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 사과 정보
        int K = Integer.parseInt(br.readLine());
        boolean[][] apple = new boolean[N][N];
        for (int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        // 움직임 정보
        int L = Integer.parseInt(br.readLine());
        int[][] move = new int[L+1][2];
        for (int i = 0; i < L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken());
            String tmp = st.nextToken();
            switch(tmp){
                case "L":
                    move[i][1] = -1;
                    break;
                case "D":
                    move[i][1] = 1;
                    break;
            }
        }
        move[L][0] = Integer.MAX_VALUE; // 마지막 방향 전환을 무한대로 위치해 놓는다


        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] snake = new boolean[N][N]; // 현재 뱀이 존재하는 위치 (뱀이 자기 몸에 부딛히는 경우 확인하기 위한 용도)

        // 1초 후의 뱀 정보 초기값 설정 (0초부터 방향이 바뀌는 경우는 없음)
        int time = 1;
        int dir = 1;
        int moveIdx = 0; // 움직임 정보 확인용 인덱스
        int r = 0;
        int c = 1;
        queue.add(new Node(0,0));
        snake[0][0] = true;

        // 벽이나 자신에게 부딛힐 때까지 진행
        outer : while (true){
            // 다음 방향 전환까지
            for (int i = 0; time < move[moveIdx][0]; time++){
//                System.out.println("time : " + time + " r : " + r + " c : " + c);
//                System.out.println(queue.size());

                // 범위 바깥으로 나가거나 자신에게 부딛히면 out
                if (r < 0 || c < 0 || r >= N || c >= N) {
//                    System.out.println("범위를 벗어남!");
                    break outer;
                }
                if (snake[r][c]) {
//                    System.out.println("자기 몸에 부딛힘!");
                    break outer;
                }

                // 뱀의 현 위치를 큐에 넣고 해당 위치를 true 처리하여 저장
                queue.add(new Node(r,c));
                snake[r][c] = true; // 현재 뱀이 차지하고 있는 범위

                // 사과 먹으면 냅두고 아니면 큐에서 꺼내고 위치 false 처리하기
                if (!apple[r][c]) {
                    Node n = queue.poll();
                    snake[n.r][n.c] = false;
                } else {
                    apple[r][c] = false;
                }

                // 뱀 이동
                r += rr[dir];
                c += rc[dir];
            }
            dir = (dir + move[moveIdx++][1] + 4) % 4;
//            System.out.println("방향전환" + dir);
        }

        System.out.println(time);


    }
}