import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int from; int to; int dist;

        public Edge(int from, int to, int dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge e){
            return this.dist - e.dist;
        }
    }
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        for (int i = 1; i <= V; i++){
            parent[i] = i;
        }

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edges);

        int count = 1;
        long total = 0;
        for (int i = 0; i < E; i++){
            if (union(edges[i].from, edges[i].to)){
                count++;
                total += edges[i].dist;
            }
            if (count == E) break;
        }

        System.out.println(total);

    }

    private static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return false;
        parent[Math.max(parentA, parentB)] = Math.min(parentA, parentB);
        return true;
    }
}