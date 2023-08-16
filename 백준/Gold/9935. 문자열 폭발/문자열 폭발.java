import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        char[] output = new char[input.length()];

        int idx = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // input의 char가 폭탄 마지막 단어이면 폭탄의 길이만큼 확인한다.
            if (c == bomb.charAt(bomb.length()-1) && idx >= bomb.length()-1){
                boolean tmp = true;
                for (int j = 1; j < bomb.length(); j++) {
                    if(output[idx-j] != bomb.charAt(bomb.length()-1-j)){
                        tmp = false;
                    }
                }
                //폭탄이 맞으면 index를 돌린다. idx를 1 안더했으니 bomb.length()에서 1 빼줌
                if (tmp) {
                    idx -= bomb.length()-1;
                    continue;
                }
                //폭탄이 아니면 넘어감
            }
            // 폭탄이 아니거면 output배열 idx에 갱신
            output[idx++] = c;
        }


        // idx가 0이라는 뜻은 남은 문자가 없다는 뜻으로 FRULA 출력
        if (idx==0){
            System.out.println("FRULA");
        }

        // idx가 남아있으면 남은 문자 출력
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idx; i++) {
                sb.append(output[i]);
            }
            System.out.println(sb);
        }



    }

}

