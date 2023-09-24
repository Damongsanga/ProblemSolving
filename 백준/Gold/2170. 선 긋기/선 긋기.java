import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });


        int srt = arr[0][0];
        int end = arr[0][1];
        int length = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i][0] > end){
                length += end-srt;
                srt = arr[i][0];
                end = arr[i][1];
            } else {
                end = Math.max(end, arr[i][1]);
            }
        }
        length += end-srt;

        System.out.println(length);


    }
}