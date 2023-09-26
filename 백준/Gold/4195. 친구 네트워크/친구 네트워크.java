import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static HashMap<String, Integer> map;
    static int[] parent;
    static int[] friends;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            map = new HashMap<>();
            int F = Integer.parseInt(br.readLine());

            parent = new int[F*2+1];
            friends = new int[F*2+1];
            for (int j = 1; j < F*2+1; j++) {
                parent[j] = j;
            }
            Arrays.fill(friends,1);

            int number = 0;
            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(a)) map.put(a, ++number);
                if (!map.containsKey(b)) map.put(b, ++number);
                sb.append(union(map.get(a), map.get(b))+"\n");

            }
        }
        System.out.print(sb);
    }
    public static int find(int a){
        if (parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static int union(int a, int b){
        int x = find(a);
        int y = find(b);

//        if (x < y){
//            friends[x] += friends[y];
//            friends[y] = friends[x];
//            parent[y] = x;
//        } else if (x > y) {
//            friends[y] += friends[x];
//            friends[x] = friends[y];
//            parent[x] = y;
//        }
        if (x != y) {
            int tmp = friends[x] + friends[y];
            friends[y] = friends[x] = tmp;
        } else {
            int tmp = Math.max(friends[x], friends[y]);
            friends[y] = friends[x] = tmp;
        }
        parent[y] = x;
        return friends[x];
    }
}

