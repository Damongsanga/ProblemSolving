import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] p = br.readLine().toCharArray();
        int[] pi = new int[p.length];

        // 실패함수 구하기
        int j = 0;
        for (int i = 1; i < p.length; i++) {
            while (j > 0 && p[j] != p[i]) {
                j = pi[j - 1]; // 마지막 접두사 패턴 (j-1)의 pi 값으로 가야 함!
            }

            if (p[j] == p[i]) j++;

            pi[i] = j;
        }

        // KMP
        j = 0;
        for (int i = 0; i < s.length; i++) {
            while (j > 0 && p[j] != s[i]) {
                j = pi[j - 1];
            }

            if (p[j] == s[i]) {
                if (j == p.length - 1) {
                    System.out.println(1);
                    System.exit(0);
                } else {
                    j++;
                }
            }

        }

        System.out.println(0);

    }


}