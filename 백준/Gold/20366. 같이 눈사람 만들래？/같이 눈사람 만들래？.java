import java.util.*;

public class Main {

    /* 아이디어 : 숫자 4개를 정하면 조합은 바깥 2개, 안쪽 2개로 무조건 정해짐
    * 바깥 2개를 조합으로 정하고 안쪽 2개는 바깥 2개에서 시작하여 수렴하는 투 포인터로 구하면 됨
    * */

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        dfs(0,0,0,N,arr);

        System.out.println(answer);

    }
    static void dfs(int depth, int srt, int end, int N, int[] arr){
        
        // 조합에서 two pointer를 돌림
        if (depth == 2){
            twoPointer(srt, end, arr);
        }
        
        // 조합 2개를 고름
        else if (depth == 1){
            for (int i = srt; i < N; i++) {
                dfs(2, srt, i, N, arr);
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                dfs(1, i, end, N, arr);
            }
        }
    }
    
    // 바깥쪽 눈 2개로 만든 눈사람과 양쪽에서 수렴하는 투포인터 탐색
    static boolean twoPointer(int srt, int end, int[] arr){
        boolean value = true;
        int A = arr[srt] + arr[end];
        int srtB = srt+1;
        int endB = end-1;
        while (srtB < endB){
            int B = arr[srtB] + arr[endB];
            if (Math.abs(A-B) < answer){
                answer = Math.abs(A-B);
                if (A > B) value = true;
                else value = false;
            }
            if (A > B) srtB++;
            else endB--;
        }
        return value;
    }

}
