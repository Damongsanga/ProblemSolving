import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if (max < arr[i]) max = arr[i];
        }

        int l = max;
        int r = sum;
        while(l < r){
            int mid = (l+r)/2;
            int count = 1;
            int tmpsum = 0;
            for (int i = 0; i < N; i++) {
                if (tmpsum + arr[i] > mid) {
                    count++;
                    tmpsum = arr[i];
                }
                else tmpsum += arr[i];
            }
            if (count <= M){
                r = mid;
            } else {
                l = mid+1;
            }
        }
        System.out.println(r);
    }

}