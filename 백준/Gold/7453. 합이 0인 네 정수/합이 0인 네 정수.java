import java.util.*;

public class Main {
    static int sum3 = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[4][N];
        long answer = 0;

        // 인풋 입력 및 정렬
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j][i] = sc.nextInt();
            }
        }

        long[] sumsof12 = new long[N*N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumsof12[size++] = arr[0][i] + arr[1][j];
            }
        }
        Arrays.sort(sumsof12);


        long[] sumsof34 = new long[N*N];
        size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumsof34[size++] = arr[2][i] + arr[3][j];
            }
        }
        Arrays.sort(sumsof34);

        int srt = 0;
        int end = N*N - 1;
        
        // binary Search
        while (srt < N * N && end >= 0){
            if (sumsof12[srt] + sumsof34[end] > 0) end--;
            else if (sumsof12[srt] + sumsof34[end] < 0) srt++;
            else {
                long tmp1 = 1;
                long tmp2 = 1;
                while(srt < N*N && end >= 0) {
                    if (srt < N*N-1 && sumsof12[srt+1] + sumsof34[end] == 0) {
                        tmp1++;
                        srt++;
                        continue;
                    }
                    if (end >= 1 && sumsof12[srt] + sumsof34[end-1] == 0) {
                        tmp2++;
                    	end--;
                        continue;
                    }
                    break;
                }
                answer += tmp1 * tmp2;
                srt++; end--;
            }
        }

        System.out.println(answer);

    }

}