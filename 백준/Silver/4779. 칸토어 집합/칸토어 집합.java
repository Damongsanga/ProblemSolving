import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            int length = (int) Math.pow(3, N);
            boolean[] check = new boolean[length];
            Merge(length, 0, check, N);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                if(check[i]) sb.append(" ");
                else sb.append("-");
            }
            System.out.println(sb);
        }

    }
    static void Merge(int length, int srt, boolean[] check, int N){
        if (length == 1){
            return;
        }

        for (int i = length/3; i < length/3 * 2; i++) {
            check[srt + i] = true;
        }
        Merge(length/3, srt, check, N);
        Merge(length/3, srt + length/3*2, check, N);
    }

}