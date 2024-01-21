import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] l = st.nextToken().toCharArray();
            char[] r = st.nextToken().toCharArray();
            char[] target = st.nextToken().toCharArray();

            boolean[][] arr = new boolean[l.length+1][r.length+1];

            arr[0][0] = true;
            for (int i = 1; i < l.length; i++) {
                if (arr[i-1][0] && l[i-1] == target[i-1]) arr[i][0] = true;
            }
            for (int i = 1; i < r.length; i++) {
                if (arr[0][i-1] && r[i-1] == target[i-1]) arr[0][i] = true;
            }
            
            for (int i = 1; i <= l.length; i++) {
                for (int j = 1; j <= r.length; j++) {
                    int now = i+j-1;
                    if ((arr[i][j-1] && target[now] == r[j-1]) || (arr[i-1][j] && target[now] == l[i-1])) {
                        arr[i][j] = true;
                    }
                }
            }

            sb.append("Data set " + tc + ": ").append(arr[l.length][r.length] ? "yes" : "no").append("\n");

        }

        System.out.println(sb);

    }

}