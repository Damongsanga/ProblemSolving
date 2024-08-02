import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static class Node implements Comparable<Node>{
        int x; int y; int no;
        Node left; Node right;

        public Node(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }

        @Override
        public int compareTo(Node o) {
            if (o.y == this.y) return this.x - o.x;
            return o.y - this.y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", no=" + no +
                    '}';
        }
    }

    static List<Integer> preList = new ArrayList<>();
    static List<Integer> postList = new ArrayList<>();
    static boolean[] visited;

    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        Node[] nodes = new Node[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        Arrays.sort(nodes);

        findChild(0, -1, nodes[0].x, nodes);
        findChild(0, nodes[0].x, 100001, nodes);

        pre(nodes[0]);
        post(nodes[0]);
        int[][] answer = new int[2][N];
        answer[0] = preList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public void findChild(int idx, int lBound, int rBound, Node[] nodes){
        Node now = nodes[idx];
        visited[idx] = true;
        int nextIndex = idx+1;
        while(nextIndex < nodes.length && (visited[nextIndex] || nodes[nextIndex].y == now.y)){
            nextIndex++;
        }
        if (nextIndex == nodes.length) return;

        Node node = nodes[nextIndex];

        if (!(lBound < node.x && node.x < rBound)) return;

        if (node.x < now.x) now.left = node;
        else now.right = node;

        findChild(nextIndex, lBound, node.x, nodes);
        findChild(nextIndex, node.x, rBound, nodes);
    }

    public void pre(Node now){
        preList.add(now.no);
        if (now.left != null) pre(now.left);
        if (now.right != null) pre(now.right);
    }

    public void post(Node now){
        if (now.left != null) post(now.left);
        if (now.right != null) post(now.right);
        postList.add(now.no);
    }

}