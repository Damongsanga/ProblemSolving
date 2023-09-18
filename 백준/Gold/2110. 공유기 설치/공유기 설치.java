import java.util.*;

public class Main {
    static int[] arr;
    static int N;
    static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        // 가장 인접한 공유기의 최대 거리 범위
        int srt = 0;
        int end = arr[N-1] - arr[0];

        int answer = 0;
        while(srt <= end){
            int mid = (srt + end) / 2;
            if (possible(mid)) {
                answer = mid;
                srt = mid + 1;
            }
            else end = mid-1;
        }
        System.out.println(answer);

    }
    static boolean possible(int mid){
        int idx = 0;
        int count = 1; // 맨 처음 집에는 무조건 공유기를 설치하는 것이 유리하다.
        int tmp = 1;
        while (idx + tmp < N){
            if (arr[idx+tmp] - arr[idx] >= mid) {
                count++;
                idx += tmp;
                tmp = 1;
            } else {
                tmp++;
            }
        }

        if (count >= M) return true;
        return false;
    }

}