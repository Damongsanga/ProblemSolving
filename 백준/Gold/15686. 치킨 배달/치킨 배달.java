import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        int r, c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Node> houseList = new ArrayList<>();
        ArrayList<Node> chickenList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) houseList.add(new Node(i,j));
                if (tmp == 2) chickenList.add(new Node(i,j));
            }
        }

        backtracking(0,0,new int[M], M, chickenList, houseList);

        System.out.println(answer);
    }

    static void backtracking(int depth, int srt, int[] chosen, int M, ArrayList<Node> chickenList, ArrayList<Node> houseList){
        if (depth == M){
            answer = Math.min(answer, chickenDistance(chosen,chickenList,houseList));
            return;
        }

        for (int i = srt; i < chickenList.size(); i++) {
            chosen[depth] = i;
            backtracking(depth+1, i+1, chosen, M, chickenList, houseList);
        }

    }

    static int chickenDistance(int[] chosen, ArrayList<Node> chickenList, ArrayList<Node> houseList){
        int min = 987654321;
        int sum = 0;
        for (int i = 0; i < houseList.size(); i++) {
            min = 987654321;
            Node h = houseList.get(i);
            for (int j = 0; j < chosen.length; j++) {
                Node c = chickenList.get(chosen[j]);
                min = Math.min(min, Math.abs(c.r - h.r) + Math.abs(c.c - h.c));
            }
            sum += min;
        }
        return sum;
    }


}