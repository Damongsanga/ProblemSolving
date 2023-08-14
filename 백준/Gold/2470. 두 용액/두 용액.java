import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int srt = 0;
        int end = N-1;
        long[] answer = {arr[srt], arr[end]};
        long max = 2000000001;
        while (srt != end){
            long now = Math.abs(arr[srt] + arr[end]);
            if (now < max){
                max = now;
                answer[0] = arr[srt];
                answer[1] = arr[end];
            }
            if (now > Math.abs(arr[srt+1] + arr[end])){
                srt++;
            } else {
                end--;
            }
        }

        System.out.print(answer[0] + " " + answer[1]);

    }
}


