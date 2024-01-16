import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        int no; int dist;

        public Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", dist=" + dist +
                    '}';
        }
    }

    static List<Node>[] arr;
    static int maxNode;
    static int maxDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            String[] tmp = br.readLine().split(" ");
            int now = Integer.parseInt(tmp[0]);
            for (int j = 1; j < tmp.length-1; j+=2) {
                arr[now].add(new Node(Integer.parseInt(tmp[j]), Integer.parseInt(tmp[j+1])));
            }
        }

        boolean[] visited = new boolean[N+1];
        dfs(1,0, visited);

        visited = new boolean[N+1];
        dfs(maxNode, 0, visited);

        System.out.println(maxDist);

    }

    static void dfs(int now, int dist, boolean[] visited){
        visited[now] = true;
        if (dist > maxDist){
            maxDist = dist;
            maxNode = now;
        }

        for (Node node : arr[now]) {
            if (visited[node.no]) continue;
            dfs(node.no, dist + node.dist, visited);
        }
    }

}