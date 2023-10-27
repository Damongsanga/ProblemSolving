import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int a; int b; int dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        public int compareTo(Edge e){
            return this.dist - e.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 || n == 0) break;
            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }
            Edge[] edges = new Edge[n];

            int totalDist = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                totalDist += edges[i].dist;
            }

            Arrays.sort(edges);

            for (int i = 0; i < n; i++) {
                if (m == 1) break;
                Edge e = edges[i];
                if (union(e.a, e.b)) {
                    m--;
                    totalDist -= e.dist;
                }
            }

//        System.out.println(Arrays.toString(parent));

            System.out.println(totalDist);

        }

    }

    static boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return false;
        parent[Math.max(parentA, parentB)] = Math.min(parentA, parentB);
        return true;
    }

    static int find(int a){
        if (parent[a] == a)  return a;
        return parent[a] = find(parent[a]);
    }

}