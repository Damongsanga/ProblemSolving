import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] p = br.readLine().toCharArray();
        int[] pi = new int[p.length];

        // 실패함수 구하기
        int j = 0;
        for (int i = 1; i < p.length; i++) { // pi[0] = 0 항상 이다
            while (j > 0 && p[j] != p[i]) { // 최소 접두사 패턴?을 찾을 떄까지 반복, j는 0보다 더 작을 수 없음!
                j = pi[j - 1]; // 마지막 접두사 패턴 (j-1)의 pi 값으로 가야 함!
            }

            if (p[j] == p[i]) j++; // 만약 패턴을 찾은거라면 j++

            pi[i] = j; // 해당 패턴 값으로 j 부여
        }

        // KMP
        j = 0;
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            while (j > 0 && p[j] != s[i]) { // 실패함수 구하는 부분과 동일
                j = pi[j - 1];
            }

            if (p[j] == s[i]) { // 역시 동일
                if (j == p.length - 1) { // 만약 모두 돌았다면
                    count++;
                    list.add(i-j+1); 
                    j = pi[j]; // 제일 큰 접두사패턴이 있는 앞으로 다시 돌아감
                } else { // 아니라면
                    j++;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(count + "\n");
        for(Integer i : list){
            sb.append(i + "\n");
        }
        System.out.println(sb);

    }


}