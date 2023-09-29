import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        final int INF = 400 * 10000 + 1;

        int[][] dist = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = c;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    dist[j][k] = Math.min(dist[j][i] + dist[i][k], dist[j][k]);
                }
            }
        }

//        int min = INF;
//        for (int i = 1; i <= V; i++) {
//            if (min > dist[i][i]) min = dist[i][i];
//        }
        int min = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V ; j++) {
                if (dist[i][j] != INF && dist[j][i] != INF && min > dist[i][i]) min = dist[i][i];
            }
        }

        System.out.println(min == INF ? -1 : min);
    }
}