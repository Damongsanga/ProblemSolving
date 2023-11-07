import java.io.*;
import java.util.*;


public class Main {
    static class Node {
        int r; int c; int changed; int dir; int dist;
        public Node(int r, int c, int changed, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.changed = changed;
            this.dir = dir;
            this.dist = dist;
        }
    }

    static int answer = Integer.MAX_VALUE;
    static int[] rr = new int[] {-1,0,1,0};
    static int[] rc = new int[] {0,1,0,-1};
    static char[][] map;
    static int W;
    static int H;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        List<Node> clist = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'C') {
                    clist.add(new Node(i,j,0,-1, 0));
                }
            }
        }
        Node srt = clist.get(0);
        Node end = clist.get(1);
        BFS(srt,end);

        System.out.println(answer);

    }

    static void BFS(Node srt, Node end) {

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) ->o1.changed - o2.changed);
        int[][][] visitedBFS = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
            	for (int k = 0; k < 4; k++) {
            		visitedBFS[i][j][k] = 987654321;
				}
            }
        }
        visitedBFS[srt.r][srt.c][0] = 0;
        visitedBFS[srt.r][srt.c][1] = 0;
        visitedBFS[srt.r][srt.c][2] = 0;
        visitedBFS[srt.r][srt.c][3] = 0;
        
        for (int i = 0; i < 4; i++) {
            int nr = srt.r + rr[i];
            int nc = srt.c + rc[i];
            if (nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
            if (map[nr][nc] == '*') continue;
            queue.add(new Node(nr,nc,0,i,1));
            visitedBFS[nr][nc][i] = 0;
        }

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.r == end.r && now.c == end.c) {
                if (now.changed < answer) answer = now.changed;
                continue;
            }

            if (now.changed >= answer) continue;

            for (int i = 0; i < 4; i++) {
                if (now.dir != -1 && Math.abs(now.dir - i) == 2) continue;
                int nr = now.r + rr[i];
                int nc = now.c + rc[i];
                if (nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
                if (map[nr][nc] == '*') continue;

                if (now.dir == i) {
                    if (visitedBFS[nr][nc][now.dir] > now.changed) {
                        visitedBFS[nr][nc][now.dir] = now.changed;
                        queue.add(new Node(nr,nc,now.changed,now.dir, now.dist+1));
                    }
                } else {
                    if (visitedBFS[nr][nc][i] > now.changed+1) {
                        visitedBFS[nr][nc][i] = now.changed+1;
                        queue.add(new Node(nr,nc,now.changed+1,i, now.dist+1));
                    }
                }

            }
        }
    }

}