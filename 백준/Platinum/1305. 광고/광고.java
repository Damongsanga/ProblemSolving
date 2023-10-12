import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] p = br.readLine().toCharArray();

        int[] pi = new int[N];

        // 실패함수 구하기
        int j = 0;
        for (int i = 1; i < N; i++) { // pi[0] = 0 항상 이다
            while (j > 0 && p[j] != p[i]) { // 최소 접두사 패턴?을 찾을 떄까지 반복, j는 0보다 더 작을 수 없음!
                j = pi[j - 1]; // 마지막 접두사 패턴 (j-1)의 pi 값으로 가야 함!
            }
            if (p[j] == p[i]) j++; // 만약 패턴을 찾은거라면 j++
            pi[i] = j; // 해당 패턴 값으로 j 부여
        }

        int repeated = N - pi[N-1];
        System.out.println(repeated);

    }
}